# Complete Pipeline (ECR + EKS) Branch

This branch documents an end-to-end Jenkins CI/CD flow for a Java Maven application, from versioned build to Docker image push in Amazon ECR and deployment to Kubernetes (EKS-style manifests).

## Project Goal
Build, package, containerize, publish, and deploy the app using Jenkins automation while applying practices demonstrated across all repository branches.

## End-to-End Pipeline Flow (`Jenkinsfile`)
1. **Increment version**
   - Uses Maven `build-helper` + `versions:set` to auto-bump patch version.
   - Creates a unique image tag with `appVersion-buildNumber`.
2. **Build application**
   - Runs Maven build to produce the JAR artifact.
3. **Build and push image to ECR**
   - Builds Docker image from `Dockerfile`.
   - Logs in securely to Amazon ECR using Jenkins credentials.
   - Pushes versioned image to ECR repository.
4. **Deploy to Kubernetes**
   - Applies `kubernetes/deployment.yaml` and `kubernetes/service.yaml`.
   - Uses environment substitution (`envsubst`) for dynamic image/app values.
5. **Commit version update back to Git**
   - Commits updated version metadata and pushes branch changes.

## DevOps Practices Used Across This Repository

### CI/CD and Pipeline Engineering
- Declarative Jenkins pipelines with staged delivery (`build`, `image`, `deploy`).
- Multi-branch pipeline strategy (baseline, versioning, SSH deployment, shared library, ECR/EKS flow).
- Build metadata tagging for traceable releases.
- Automated version management in pipeline.

### Build and Quality
- Maven-based Java build lifecycle.
- Clean packaging practices (`mvn clean package` / `mvn clean verify`).
- Unit test coverage through JUnit test structure.

### Containerization and Registry
- Dockerized Spring Boot artifact delivery.
- Versioned image tagging strategy.
- Registry push workflows used for both:
  - Docker Hub (other branches)
  - Amazon ECR (this branch)

### Deployment Patterns
- Kubernetes manifest-based deployment (`Deployment` + `Service`).
- Environment-templated manifests for reusable deployments.
- Remote VM deployment pattern via `sshagent` + `scp` + `ssh` + Docker Compose (SSH-agent branch).
- EC2 runtime operations practices (service orchestration and remote command execution).

### Reusability and Maintainability
- Jenkins Shared Library usage in dedicated branch (`buildJar`, `buildImage`, `dockerLogin`, `dockerPush`).
- Separation of concerns between build, image, and deploy stages.
- Reusable scripts/manifests for operational consistency.

### Security and Credential Handling
- Jenkins credentials binding for registry logins and cloud keys.
- Secret values injected at runtime instead of hardcoding.
- Controlled remote access via Jenkins-managed SSH keys.

## Repository Areas Referenced
- `Jenkinsfile` (pipeline logic)
- `Dockerfile` (image build definition)
- `pom.xml` (build/version config)
- `kubernetes/deployment.yaml` and `kubernetes/service.yaml` (K8s deployment)
- `docker-compose.yaml` and `server-cmds.sh` (SSH-agent deployment pattern)

## Local Validation
```bash
mvn clean verify
```

## Notes
- App container exposes `8080`.
- Kubernetes service maps traffic to the application container port.
- This repository is designed as a progressive DevOps portfolio: each branch demonstrates a practical CI/CD capability that contributes to the complete ECR + EKS pipeline story.
