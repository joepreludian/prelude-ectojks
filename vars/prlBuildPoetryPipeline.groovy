/*
 * Pipeline for Poetry build
 */

def prlBuildFancyDescription(Map conf = [header: 'Header', cols: [], rows: []]) {

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []

    table_cols = ''
    cols.each { item ->
        table_cols << "<th>${item}</th>"
    }

    table_rows = ''
    rows.each { line ->
        table_rows << '<tr>'
        item.each { col ->
            table_rows << "<td>${col}</td>"
        }
        table_rows << '</tr>'
    }

    html_content = """<h4>${header}</h4>
        <table class='table-striped table-bordered table-sm'>
            <thead>
                <tr>${table_cols}</tr>
            </thead>
            <tbody>
                ${table_rows}
            </tbody>
        </table>"""

    currentBuild.rawBuild.project.description = html_content
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
                            rows: [['Test1', 'test2'], ['Test3', 'Test 4']]
                    ])
                }
            }
        }
    }
}

