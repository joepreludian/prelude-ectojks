/*
 * Pipeline for Poetry build
 */

import com.preludian.ectojs.JenkinsUtils

def call(Map conf = [:]) {

    pipeline {
        agent any
        stages {
            stage('set diff vars') {
                steps {
                    script {
                        return_whoami = sh script: 'whoami', returnStdout: true
                    }
                }
            }
            stage('Gen Table') {
                steps {
                    script {
                        jenkinsUtils = new JenkinsUtils()

                        jenkinsUtils.buildFancyDescription([
                                header: "Whoami: ${return_whoami}",
                                displayName: 'myBuild',
                                setRootBuild: true,
                                cols: ['Project', 'Version', 'Test'],
                                rows: [
                                        ['Project name', 'Temp version', 'Test info'],
                                        ['Test3', 'Test 4', 'Test 5']
                                ]
                        ])
                    }
                }
            }
        }
    }
}

