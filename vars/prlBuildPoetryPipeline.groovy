/*
 * Pipeline for Poetry build
 */

def prlBuildFancyDescription(Map conf = [header: 'Header', cols: [], rows: []]) {

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []

    table_header = cols.join('</th><th>')
    html_content = "<h4>${header}</h4><table><tr>${table_header}</th></table>"

    currentBuild.rawBuild.project.description = html_content
}

def call(Map conf = [:]) {

    pipeline {
        agent any
        stages {
            stage('Testing') {
                steps {
                    prlBuildFancyDescription(header: 'Table', cols: ['Col1', 'Column2'], rows: ['Test1', 'test2'])
                }
            }
        }
    }
}

