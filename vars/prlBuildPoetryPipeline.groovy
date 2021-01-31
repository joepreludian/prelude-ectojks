/*
 * Pipeline for Poetry build
 */

def prlBuildFancyDescription(Map conf = [header: 'Header', cols: [], rows: []]) {

    header = conf['header'] ?: 'Default Header'
    cols = conf['cols'] ?: []
    rows = conf['rows'] ?: []

    table_cols = ''
    cols.each { item ->
        table_cols.concat("<th>${item}</th>")
    }

    html_content = "<h4>${header}</h4><table class='table table-striped table-bordered table-sm'><tr>${table_cols}</tr></table>"

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
                            rows: ['Test1', 'test2']
                    ])
                }
            }
        }
    }
}

