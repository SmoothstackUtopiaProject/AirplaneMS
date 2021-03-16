pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                script {
                    sh "mvn clean package"
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh "docker build -t utopiaairplanems ."
                sh "docker tag utopiaairplanems:latest public.ecr.aws/v8i4g2b7/utopiaairplanems:latest"
                sh "docker push public.ecr.aws/v8i4g2b7/utopiaairplanems:latest"
            }
        }
    }
}
