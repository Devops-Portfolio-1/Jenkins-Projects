#!/usr/bin/env groovy

pipeline {
    agent any 
    tools {
        maven 'maven-3.9.13'
    }
    stages {

        stage('increment version') {
            steps {
                script {
                    echo "Incrementing the version..."
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion} versions:commit'

                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    /**
                     * Accesses the first match group from the matcher object.
                     * matcher[0][1] refers to:
                     * - matcher[0]: The first match found by the regular expression (typically the version tag in the pom file).
                     * - matcher[0][1]: The first capturing group within that match (usually the actual version string inside the <version> tag).
                     */
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                    // adding the build numnber as the suffix to the version number to make it unique for each build, some ppl add the git commit hash as well to make it more unique.
                }
            }
        } 
        
        stage('build jar') {
            steps {
                script {
                    echo "Building the application..."
                    sh 'mvn clean package'
                    // each time in a build a target folder will get created , so with this command mvn will clear all the files and directories in the target folder of previous build and then create a new jar file in the target folder for the current build.
                }
            }
        }
       
        stage('build image') {
            steps {
                script {
                    echo "Testing the application..."
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh "docker build -t shalindra936/java-maven-app:${IMAGE_NAME} ."
                    sh 'echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin'
                    sh "docker push shalindra936/java-maven-app:${IMAGE_NAME}"
                    }
                }
            }
        
        }
        
    }
}

