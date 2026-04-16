pipeline {
    agent any

    tools {
        // This name must match the name in 'Manage Jenkins' -> 'Global Tool Configuration'
        maven 'Maven'
    }

    environment {
        GITHUB_URL = "https://github.com/senthilaravinth/Gyu.git"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pulls the code from your repository
                checkout scm
            }
        }

        stage('Maven Build & Test') {
            steps {
                // Runs the tests we wrote for your attendance app
                bat 'mvn clean test'
            }
        }

        stage('Maven Package') {
            steps {
                // Creates the .jar file in the target folder
                bat 'mvn package -DskipTests'
            }
        }

        stage('Push to GitHub') {
            steps {
                script {
                    // This block allows Jenkins to use your GitHub login securely
                    withCredentials([usernamePassword(credentialsId: 'github-creds', passwordVariable: 'GIT_PASS', usernameVariable: 'GIT_USER')]) {
                        
                        // We use 'bat' because your Jenkins is on Windows
                        // 1. Set the remote URL with credentials
                        bat "git remote set-url origin https://%GIT_USER%:%GIT_PASS%@github.com/senthilaravinth/Gyu.git"
                        
                        // 2. Add files, commit, and push
                        // Note: Jenkins usually runs in a 'detached HEAD' state, so we push back to 'main'
                        bat "git add ."
                        bat 'git commit -m "Build successful - Updated by Jenkins [Build ${env.BUILD_NUMBER}]"'
                        bat "git push origin HEAD:main"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Successfully ran Maven and pushed updates to GitHub!"
        }
        failure {
            echo "Pipeline failed. Check the Maven logs or Git credentials."
        }
    }
}