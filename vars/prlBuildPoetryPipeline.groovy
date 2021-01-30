/*
 * Pipeline for Poetry build
 */

def setBuildDetails() {
    currentBuild.rawBuild.project.description = '<h3>Python Poetry</h3><p>Temp</p>'
}

def call(Map conf = [:]) {

    pipeline {
        agent any
        stages {
            stage('Testing') {
                steps {
                    setBuildDetails()
                }
            }
        }
    }
}

