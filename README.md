# Jenkins CI/CD Portfolio Project (SSH Agent Deployment)

This repository demonstrates a complete DevOps workflow for a Java Maven application using Jenkins, Docker, and AWS EC2 deployment over SSH credentials managed by Jenkins.

## Project Summary

- Builds a Java Spring Boot application with Maven
- Packages and pushes a Docker image to Docker Hub
- Deploys to an EC2 host using `sshagent` in Jenkins
- Starts services with Docker Compose on the target server

This project is designed to showcase practical CI/CD engineering skills that are relevant to real production delivery pipelines.

## What This Project Demonstrates

- Jenkins Declarative Pipeline design
- Reusable Jenkins Shared Library usage (`buildJar`, `buildImage`, `dockerLogin`, `dockerPush`)
- Secure deployment with Jenkins-managed SSH credentials (`ec2-server-key`)
- Automated remote deployment using `scp` + `ssh`
- Containerized application delivery using Docker and Docker Compose

## Repository Structure

- `Jenkinsfile` – CI/CD pipeline (build, image, deploy)
- `pom.xml` – Maven project configuration
- `Dockerfile` – container build definition
- `docker-compose.yaml` – runtime services on server
- `server-cmds.sh` – remote deployment command wrapper
- `src/` – Java source code and tests

## CI/CD Pipeline Flow

1. **Build App**  
   Jenkins builds the application JAR with Maven through shared library logic.

2. **Build & Push Image**  
   Jenkins builds Docker image `shalindra936/java-mvn-app:1.1.5-8`, logs in to Docker Hub, and pushes the image.

3. **Deploy to EC2 via SSH Agent**  
   Jenkins uses `sshagent(['ec2-server-key'])` to:
   - copy deployment files to EC2 (`server-cmds.sh`, `docker-compose.yaml`)
   - execute remote deployment commands
   - run Docker Compose with the selected image

## Deployment Model

- Remote host: EC2 Linux instance
- Authentication: Jenkins SSH credential (`ec2-server-key`)
- Runtime stack:
  - `java-maven-app` container on port `8080`
  - `postgres:13` container on port `5432`

## Local Validation

```bash
mvn clean verify
```

## Why This Is Recruiter-Relevant

This branch highlights practical DevOps capabilities expected in junior-to-mid platform and CI/CD roles:

- End-to-end pipeline ownership from build to deployment
- Security-aware automation using managed credentials
- Container-first deployment design
- Cloud-hosted operational delivery (EC2 + Docker)

---

If you are reviewing this as part of my portfolio, I can also provide:
- architecture diagrams
- Jenkins job screenshots
- deployment logs
- branch-by-branch enhancement notes
