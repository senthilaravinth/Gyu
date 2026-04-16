pipeline {
    agent any

    tools {
        // Must match the name in Global Tool Configuration
        maven 'Maven'
    }

    environment {
        // Define your Docker image name here
        IMAGE_NAME = "student-attendance-app"
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Maven Build & Test') {
            steps {
                echo 'Building Jar file...'
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Creating Docker Image...'
                // Ensure there is a SPACE between docker and build
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Docker Verify') {
            steps {
                echo 'Verifying Image...'
                bat "docker images | findstr ${IMAGE_NAME}"
            }
        }
        
        stage('Cleanup') {
            steps {
                echo 'Removing unused build layers...'
                // Optional: Cleans up dangling images to save space on your C: drive
                bat 'docker image prune -f'
            }
        }
    }

    post {
        success {
            echo "Successfully built Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "Pipeline failed. Check if Docker Desktop is running."
        }
    }
}