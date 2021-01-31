/*
 * Pipeline for Poetry build
 */

def prlBuildFancyDescription(Map conf = [header: 'Header', cols: [], rows: []]) {

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []

    def table_cols = ''
    cols.each { item ->
        table_cols << "<th>${item}</th>"
    }

    def table_rows = ''
    rows.each { line ->
        table_rows << '<tr>'
        line.each { col ->
            table_rows << "<td>${col}</td>"
        }
        table_rows << '</tr>'
    }
    echo table_rows

    html_content = """<h4>${header}</h4>
        <table border=1>
            <tr>${table_cols}</tr>
            ${table_rows}
        </table>"""

    echo html_content

    currentBuild.rawBuild.project.description = html_content

    currentBuild.description = html_content
    currentBuild.displayName = "Display Name"
}

def call(Map conf = [:]) {

    pipeline {
        agent any
        stages {
            stage('Gen Table') {
                steps {
                    prlBuildFancyDescription([
                            header: 'Main Table header',
                            cols: ['Main Column', 'Secondary Column'],
                            rows: [
                                    ['Test1', 'test2'],
                                    ['Test3', 'Test 4']
                            ]
                    ])
                }
            }
        }
    }
}

