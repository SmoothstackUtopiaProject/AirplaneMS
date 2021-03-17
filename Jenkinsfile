pipeline {
    agent any

    stages {
        stage('Package') {
            steps {
                echo 'Building..'
                script {
                    sh "mvn clean package"
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Deploying....'
                sh "aws ecr get-login-password --region us-east-1 --profile=default | docker login --username AWS --password-stdin 466486113081.dkr.ecr.us-east-1.amazonaws.com"                
                sh "docker build -t utopiaairplanems ."
                sh "docker tag utopiaairplanems:latest 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:latest"
                sh "docker push 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:latest"
            }
        }
        stage('Deploy') {
           steps {
               sh "rm ECSService.yml"
               sh "wget https://github.com/SmoothstackUtopiaProject/CloudFormationTemplates/blob/main/ECSService.yml"
               sh "aws cloudformation create-stack --stack-name UtopiaAirplaneMS --template-body ./ECSService.yml --capabilities \"CAPABILITY_IAM\" \"CAPABILITY_NAMED_IAM\""
           }
        }
    }
}
