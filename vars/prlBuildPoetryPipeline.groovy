/*
 * Pipeline for Poetry build
 */

def prlBuildFancyDescription(Map conf = [header: 'Header', cols: [], rows: []]) {

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []

    table_cols = new StringBuffer()
    cols.each { item ->
        table_cols.append("<th>${item}</th>")
    }
    print table_cols

    table_rows = new StringBuffer()
    rows.each { line ->
        table_rows << '<tr>'
        line.each { col ->
            table_rows << "<td>${col}</td>"
        }
        table_rows << '</tr>'
    }
    print table_rows

    html_content = """
        <table border="1">
            <tr>${table_cols}</tr>
            ${table_rows}
        </table>
        <h4>${header}</h4>
    """

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

