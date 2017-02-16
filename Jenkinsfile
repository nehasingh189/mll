pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'cd src/main/webapp && npm install && bower install && gulp cleanup && gulp build && gulp test'
            }
        }
    }
}
