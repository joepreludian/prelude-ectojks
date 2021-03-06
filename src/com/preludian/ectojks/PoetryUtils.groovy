package com.preludian.ectojks

import com.preludian.ectojks.JenkinsUtils

def getPoetryMetadata() {

    jenkinsUtils = new JenkinsUtils()
    jsonOutput = null

    jenkinsUtils.loadFilesIntoWorkspace(
        package: 'com/preludian/python',
        files: ['convert_toml.py']
    )

    // @todo Add way to select a different node if needed

    docker.image('docker.io/joepreludian/python-poetry:latest').inside {
        sh 'pip install toml==0.10.2'

        def jsonText = sh (script: 'python convert_toml.py --input pyproject.toml --output STDOUT',
                returnStdout: true).trim()

        jsonOutput = readJSON text: jsonText, returnPojo: true
    }

    return [
        fullOutput: jsonOutput,
        name: jsonOutput['tool']['poetry']['name'],
        version: jsonOutput['tool']['poetry']['version'],
        description: jsonOutput['tool']['poetry']['description']
    ]
}

return this