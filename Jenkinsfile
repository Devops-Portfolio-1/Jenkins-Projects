#!/usr/bin/env groovy

@Library('jenkins-shared-library') 
def gv


pipeline {
    agent any 
    tools {
        maven 'maven-3.9.13'
    }

    stages {
        
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    buildJar()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    echo "building image "
                    buildImage 'shalindra936/java-maven-app:jma-2.0'
                    dockerLogin()
                    dockerPush 'shalindra936/java-maven-app:jma-2.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    
                }
            }
        }
    }   
}