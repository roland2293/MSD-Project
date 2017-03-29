pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -f DBLP/pom.xml compile'
            }
        }
        stage('Test') {
            steps {
            wrap([$class: 'Xvnc', useXauthority: true]) {
                    sh 'mvn -f DBLP/pom.xml test'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn -f DBLP/pom.xml package'
                archiveArtifacts artifacts: '**/target/*.jar, **/target/site/**/**.*', fingerprint: true
            }
        }
    }
}