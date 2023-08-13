pipeline{
    agent any
    
    environment {
        DOCKER_IMAGE_NAME = 'ram2715/boot-sonar-docker'
        DOCKERFILE_PATH = 'Dockerfile'
        REGISTRY_URL = 'docker.io'
        REGISTRY_CREDENTIALS_ID = 'docker-registry-credentials'
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
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        withSonarQubeEnv('MySonarQubeServer') { 
        // If you have configured more than one global server connection, you can specify its name
//      sh "${scannerHome}/bin/sonar-scanner"
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
                    docker.withRegistry(env.REGISTRY_URL, env.REGISTRY_CREDENTIALS_ID) {
                        def dockerImage = docker.image(env.DOCKER_IMAGE_NAME)
                        dockerImage.push()
                    }
                }
            }
            
        }
       
    }
}