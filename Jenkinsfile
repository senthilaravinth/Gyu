pipeline {
    agent any

    tools {
        // Ensure this name matches what you configured in: 
        // Manage Jenkins -> Global Tool Configuration -> Maven
        maven 'Maven'
    }

    stages {
        stage('Clean & Initialize') {
            steps {
                echo 'Cleaning the workspace...'
                bat 'mvn clean'
            }
        }

        stage('Maven Test') {
            steps {
                echo 'Running Unit Tests...'
                // This runs the AppTest.java we fixed earlier
                bat 'mvn test'
            }
        }

        stage('Maven Compile & Package') {
            steps {
                echo 'Compiling and creating the JAR file...'
                // This skips tests because we already ran them in the previous stage
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Results') {
            steps {
                echo 'Storing the build artifacts...'
                // This saves the resulting JAR file so you can download it from the Jenkins UI
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            // This pulls the test results into the Jenkins "Test Result Trend" graph
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "Build Successful! You can find the JAR file in the 'target' folder."
        }
        failure {
            echo "Build Failed. Please check the 'Console Output' to see the Java/Maven errors."
        }
    }
}