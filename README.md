# Jenkins Projects (Java + Maven + Docker + Jenkins)

This repository is a  **Java Spring Boot + Maven** application used to demonstrate **Jenkins CI/CD pipeline patterns** and **containerization with Docker**.

---

## What’s in this repo (main branch)

- **Java app (Spring Boot)** in `src/main/java/com/example/Application.java`
  - Starts a Spring Boot application and logs `"Java app started"` on startup.
  - Includes a simple `getStatus()` method returning `"OK"`.
- **Static web page** in `src/main/resources/static/index.html`
  - Displays “Welcome to Java Maven Application”.
- **Unit test** in `src/test/java/AppTest.java`
  - Tests `getStatus()` returns `"OK"`.
- **Maven build** via `pom.xml`
  - Artifact: `com.example:java-maven-app`
  - Version: `1.1.0-SNAPSHOT`
  - Java target: 1.8
- **Docker image** via `Dockerfile`
  - Base image: `amazoncorretto:8-alpine`
  - Exposes port `8080`
  - Runs the packaged JAR from `target/`
- **Jenkins pipeline** via `Jenkinsfile`
  - Currently a baseline pipeline skeleton with 3 stages:
    - `build jar`
    - `build image`
    - `deploy`

---

## Branches and what they represent

This repo includes multiple branches that show different Jenkins pipeline improvements/approaches:

- **`main`**
  - Baseline Java/Maven app + Dockerfile + a simple (placeholder) Jenkins pipeline.
- **`feature/jenkinsfile-sshagent`**
  - Adds additional DevOps assets and documentation:
    - `ReadMe.md`
    - `docker-compose.yaml`
    - `server-cmds.sh`
    - `images/` directory
  - Jenkinsfile is expanded compared to `main` and is oriented around an SSH-agent based approach.
- **`feature/app-versioning`**
  - Focuses on application/pipeline versioning.
  - Jenkinsfile is significantly expanded (larger and more detailed than `main`).
  - Dockerfile differs from `main` (extended to support the versioning approach).
- **`jenkins-shared-lib`**
  - Contains a Jenkinsfile variant aligned with using or demonstrating a shared-library style organization.
- **`jenkins-branch`**
  - Additional experimental/training branch (present in the repo branch list).
- **`copilot/create-readme-file`**
  - A working branch likely created for README authoring.

---

## Build and run locally

### Prerequisites
- Java 8+ (project targets Java 8)
- Maven
- Docker (optional, for container builds)

### Build the JAR
```bash
mvn clean package
```

### Run locally (without Docker)
```bash
java -jar target/java-maven-app-*.jar
```

Then open:
- http://localhost:8080

### Build the Docker image
```bash
docker build -t java-maven-app .
```

### Run the container
```bash
docker run --rm -p 8080:8080 java-maven-app
```

---

## CI/CD with Jenkins

The repository contains Jenkins pipeline definitions (`Jenkinsfile`) that demonstrate different pipeline patterns across branches:

- On `main`, the pipeline stages are present as a **starter template**.
- On feature branches (notably `feature/app-versioning` and `feature/jenkinsfile-sshagent`), the pipeline is expanded to demonstrate more realistic CI/CD behavior (e.g., versioning and SSH-agent based flows).

To use:
1. Create a Jenkins Pipeline job pointing to this repository.
2. Select the branch you want (`main` for baseline, feature branches for more advanced examples).
3. Ensure Jenkins has the required tools configured (JDK, Maven, Docker access, and any required credentials for the chosen branch’s approach).

---

## Project structure

```text
.
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── src
    ├── main
    │   ├── java/com/example/Application.java
    │   └── resources/static/index.html
    └── test
        └── java/AppTest.java
```

---

## Notes

- The Dockerfile expects the Maven build output at `target/java-maven-app-*.jar`.
- The app listens on port **8080**.
- The test suite includes a basic JUnit test validating the application status method.

---

## License

No license file is currently included in this repository.