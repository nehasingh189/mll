p
peline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'cd src/main/webapp && gulp clean && gulp build && gulp test'
            }
        }
    }
}
