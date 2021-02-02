package com.preludian.ectojks

Map getPoetryMetadata(Map conf = [:]) {

    jenkinsUtils = new JenkinsUtils()
    jsonOutput = null

    node {
        jenkinsUtils.loadFilesIntoWorkspace(
            package: 'com/preludian/python',
            files: ['convert_toml.py']
        )

        docker.image('docker.io/joepreludian/python-poetry:latest') {
            sh 'pip install toml==0.10.2'

            def jsonText = sh (script: 'python convert_toml.py --input pyproject.toml --output STDOUT',
                    returnStdout: True).trim()

            jsonOutput = readJson text: jsonText, returnPojo: true
        }
    }

    return jsonOutput
}

return this