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
                sh 'mvn -f DBLP/pom.xml test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn -f DBLP/pom.xml package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: '**/target/site/jacoco/index.html', fingerprint: true
                archiveArtifacts artifacts: '**/target/site/jacoco/jacoco-resources/', fingerprint: true
            }
        }
    }
}