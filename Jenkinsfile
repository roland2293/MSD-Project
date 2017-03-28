pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'cd DBLP'
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn package'
		        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}