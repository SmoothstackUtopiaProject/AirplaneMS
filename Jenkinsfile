pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                pwd
                //mvn clean package
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                //docker build -t utopiaairplanems .
                //docker tag utopiaairplanems:latest public.ecr.aws/v8i4g2b7/utopiaairplanems:latest
                //docker push public.ecr.aws/v8i4g2b7/utopiaairplanems:latest
            }
        }
    }
}
