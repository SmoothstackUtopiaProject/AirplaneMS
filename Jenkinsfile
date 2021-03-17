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
                sh "export COMMIT_HASH=\$(git rev-parse --short HEAD)"
                sh "env"
                sh "docker tag utopiaairplanems:$COMMIT_HASH 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:$COMMIT_HASH"
                sh "docker push 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:$COMMIT_HASH"
            }
        }
        stage('Deploy') {
           steps {
               sh "echo $COMMIT_HASH"
               sh "rm ECSService.yml"
               sh "wget https://raw.githubusercontent.com/SmoothstackUtopiaProject/CloudFormationTemplates/main/ECSService.yml"
               sh "aws cloudformation deploy --stack-name UtopiaAirplaneMS --template-file ./ECSService.yml --parameter-overrides ECRepositoryUri=466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:$COMMIT_HASH --capabilities \"CAPABILITY_IAM\" \"CAPABILITY_NAMED_IAM\""
           }
        }
    }
}
