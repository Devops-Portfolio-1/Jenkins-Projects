def buildJar() {
    echo "Building the JAR file..."
    sh 'mvn package'
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh 'docker build -t shalindra936/java-maven-app:jma-1.3 .'
        sh 'echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin'
        sh 'docker push shalindra936/java-maven-app:jma-1.3'
    }
}

def deployApp() {
    echo "Deploying the application..."
}

return this 