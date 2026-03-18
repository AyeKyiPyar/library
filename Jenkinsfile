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
        MYSQL_CONTAINER = "mysql-docker"
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

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Code Quality') {
            parallel {

                stage('Checkstyle') {
                    steps {
                        sh 'mvn checkstyle:checkstyle'
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

        /*stage('Code Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh '''
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=lib-demo \
                        -Dsonar.projectName=lib-demo \
                        -Dsonar.host.url=$SONAR_URL
                    '''
                }
            }
        }*/
        stage('Code Analysis') {
		    steps {
		        withSonarQubeEnv('sonar') {
		            sh '''
		                mvn clean verify sonar:sonar \
		                -Dsonar.projectKey=lib-demo \
		                -Dsonar.projectName=lib-demo \
		                -Dsonar.host.url=$SONAR_URL \
		                -Dsonar.login=$SONAR_AUTH_TOKEN
		            '''
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
                sh """
                    docker run --rm ^
                        --network ${DOCKER_NETWORK} ^
                        -v \$PWD:/app ^
                        -w /app ^
                        maven:3.9-eclipse-temurin-21 ^
                        mvn verify -Pacceptance
                """
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/cucumber-reports/*.xml'

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