#!/usr/bin/env groovy
// My First Jenkins Pipeline Script

pipeline {
    agent any 
    tools {
        maven 'maven-3.9.13'
    }
    stages {
        stage('init'){
            steps {
                script {
                    echo "Initializing the pipeline..."
                    gv = load 'script.groovy'
                }
            }
        }
        stage('build jar') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
                }
            }

