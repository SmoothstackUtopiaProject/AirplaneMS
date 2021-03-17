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
                //sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/v8i4g2b7"
                sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 466486113081.dkr.ecr.us-east-1.amazonaws.com"
                
                
                sh "docker build -t utopiaairplanems ."
                //sh "docker tag utopiaairplanems:latest public.ecr.aws/v8i4g2b7/utopiaairplanems:latest"
                sh "docker tag utopiaairplanems:latest 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:latest"
                
                //sh "docker push public.ecr.aws/v8i4g2b7/utopiaairplanems:latest"
                sh "docker push 466486113081.dkr.ecr.us-east-1.amazonaws.com/utopiaairlines/airplanems:latest"
            }
        }
        //stage('Deploy') {
          //  steps {
            //    sh "aws cloudformation create-stack --stack-name myteststack --template-body  \\
              //      file://template.yml --parameters \\ 
                //    ParameterKey=ApplicationEnvironment, ParameterValue=$ApplicationEnvironment  \\
                  //  ParameterKey=DBName, ParameterValue=$DBname"
           // }
        //}
    }
}
