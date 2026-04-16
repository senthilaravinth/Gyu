pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = "student-attendance-app"
        IMAGE_TAG = "${env.BUILD_NUMBER}"
        // Path to your kubeconfig if Jenkins is running as a service
        // KUBECONFIG = "C:/Users/Senthil Aravinth S/.kube/config" 
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
                echo 'Deploying to Kubernetes Cluster...'
                // Using 'bat' to trigger kubectl apply
                // This applies all yaml files in your k8s folder
                bat "kubectl apply -f k8s/deployment.yaml"
                
                echo 'Verifying Deployment...'
                bat "kubectl get deployments"
                bat "kubectl get pods"
            }
        }
    }

    post {
        success {
            echo "Successfully deployed version ${IMAGE_TAG} to Kubernetes!"
        }
        failure {
            echo "Deployment failed. Check if Minikube/Docker K8s is running."
        }
    }
}