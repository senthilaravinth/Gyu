pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = "student-attendance-app"
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Maven Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker Image...'
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                echo 'Deploying from Root Folder...'
                // Changed from k8s/deployment.yaml to deployment.yaml
                bat "kubectl apply -f deployment.yaml"
                
                echo 'Verifying Status...'
                bat "kubectl get pods"
            }
        }
    }

    post {
        success {
            echo "Successfully deployed to Kubernetes from the root directory!"
        }
        failure {
            echo "Build failed. Ensure deployment.yaml is in the project root."
        }
    }
}