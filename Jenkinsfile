pipeline{
    agent any
    
    environment {
        DATE_TIME = new Date().format('yyyyMMdd-HH-mm-ss')
        DOCKER_IMAGE_NAME = "ram2715/boot-sonar-docker-${DATE_TIME}"
        DOCKERFILE_PATH = 'Dockerfile'
        DOCKER_URL = 'docker.io'
        DOCKERHUB_CREDENTIALS = credentials('dockerpassword')
    }
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }
    
    stages{
       stage('GetCode'){
            steps{
                git 'https://github.com/ram-chadar/Boot_Sonar_Docker_Injenkins.git'
            }
         }        
       stage('Build'){
            steps{
               bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
         }
        stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv('MySonarQubeServer') { 
                bat "mvn sonar:sonar"
    }
        }
        }
        
        stage('Build Docker Image') {
            steps {
                
                script {
                    def dockerImage = docker.build(env.DOCKER_IMAGE_NAME, '-f ' + env.DOCKERFILE_PATH + ' .')
                }
            }
        }
        
         stage('Push Docker Image') {
            steps {
                script {
                   withCredentials([string(credentialsId: 'dockertoken', variable: 'dockertoken')]) {
    bat "docker login -u ram2715 -p ${dockertoken}"
    //bat "docker rmi ram2715/boot-sonar-docker"
    bat "docker push ${env.DOCKER_IMAGE_NAME}"
    
                    }
                }
            }
        }    
    }
     post {
        success {
            echo 'Pipeline succeeded! Docker image pushed successfully.'
        }
        failure {
            echo 'Pipeline failed! Check logs for details.'
        }
    } 
    
}
