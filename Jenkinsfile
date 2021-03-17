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
               sh 
                "aws cloudformation create-stack --stack-name UtopiaAirplaneMS --template-body
                https://github.com/SmoothstackUtopiaProject/CloudFormationTemplates/blob/main/ECSService.ymll --parameters
                ParameterKey=ApplicationEnvironment, ParameterValue=$ApplicationEnvironment,
                ParameterKey=ApplicationName, ParameterValue=UtopiaAirplaneMS,
                ParameterKey=DBHost, ParameterValue=$DB_HOST,
                ParameterKey=DBName, ParameterValue=$DB_NAME,
                ParameterKey=DBPort, ParameterValue=$DB_PORT,
                ParameterKey=ECRepositoryURI, ParameterValue=466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:latest,
                ParameterKey=SecurityGroupID, ParameterValue=$SECURITYGROUPID,
                ParameterKey=SubnetID, ParameterValue=$SUBNETID
                "
           }
        }
    }
}
