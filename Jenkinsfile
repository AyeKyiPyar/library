pipeline {
    agent any

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    tools {
        maven 'maven3.9'
    }

    environment {
        IMAGE_NAME = "library"
        CONTAINER_NAME = "library-container"
        VERSION = "${BUILD_NUMBER}"
        SONAR_URL = "http://sonar:9000"
        DOCKER_NETWORK = "jenkins-network"
        SONAR_AUTH_TOKEN = "auth-token"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/AyeKyiPyar/library.git'
            }
        }

        stage('Build & Unit Test') {
            steps {
                sh 'mvn -B clean test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Quality') {
            parallel {

                stage('Checkstyle') {
                    steps {
                        sh 'mvn checkstyle:check'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing: true,
                                keepAll: true,
                                alwaysLinkToLastBuild: true,
                                reportDir: 'target/site',
                                reportFiles: 'checkstyle.html',
                                reportName: 'Checkstyle Report'
                            ])
                        }
                    }
                }

                stage('Coverage') {
                    steps {
                        sh 'mvn jacoco:report'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing: true,
                                keepAll: true,
                                alwaysLinkToLastBuild: true,
                                reportDir: 'target/site/jacoco',
                                reportFiles: 'index.html',
                                reportName: 'Coverage Report'
                            ])
                        }
                    }
                }
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=lib-demo \
                        -Dsonar.projectName=lib-demo \
                        -Dsonar.host.url=$SONAR_URL \
                        -Dsonar.login=$SONAR_AUTH_TOKEN
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                sh """
                    docker build \
                    -t ${IMAGE_NAME}:${VERSION} \
                    -t ${IMAGE_NAME}:latest .
                """
            }
        }

        stage('Deploy Container') {
            steps {
                sh """
                    docker stop ${CONTAINER_NAME} || true
                    docker rm ${CONTAINER_NAME} || true
                    docker run -d \
                        --name ${CONTAINER_NAME} \
                        --network ${DOCKER_NETWORK} \
                        -p 8083:8081 \
                        ${IMAGE_NAME}:${VERSION}
                """
            }
        }

        stage('Acceptance Test') {
            steps {
                sh 'mvn verify -Pacceptance'
            }
            post {
                always {
                    // Proper Failsafe reporting
                    junit '**/target/failsafe-reports/*.xml'

                    // Cucumber report if available
                    publishHTML(target: [
                        allowMissing: true,
                        keepAll: true,
                        alwaysLinkToLastBuild: true,
                        reportDir: 'target/cucumber-reports',
                        reportFiles: 'cucumber-report.html',
                        reportName: 'Acceptance Report'
                    ])
                }
            }
        }

    }

    post {
        success {
            echo "✅ PIPELINE SUCCESS"
            emailext(
                to: 'ayekyipyarshwe@gmail.com',
                subject: "Build SUCCESS #${BUILD_NUMBER}",
                body: "Build #${BUILD_NUMBER} completed successfully."
            )
        }

        unstable {
            echo "⚠️ PIPELINE UNSTABLE - Check test failures or quality gate"
            emailext(
                to: 'ayekyipyarshwe@gmail.com',
                subject: "Build UNSTABLE #${BUILD_NUMBER}",
                body: "Build #${BUILD_NUMBER} is unstable. Please check Jenkins test reports."
            )
        }

        failure {
            echo "❌ PIPELINE FAILED"
            emailext(
                to: 'ayekyipyarshwe@gmail.com',
                subject: "Build FAILED #${BUILD_NUMBER}",
                body: "Build #${BUILD_NUMBER} failed. Please check Jenkins."
            )
        }
    }
}