# 🚀 Deployment

## Deployment Architecture

```text
GitHub Repository
        │
        ▼
 GitHub Actions (CI/CD)
        │
        ├── Gradle Build
        ├── Docker Image Build
        ├── Push to Docker Hub
        ▼
        EC2 (Ubuntu)
        │
        ├── docker-compose pull
        ├── docker-compose up -d
        ▼
     Nginx (Reverse Proxy)
        │
        ▼
 Spring Boot Container
```

---

## Deployment Environment

| Component               | Description                |
| ----------------------- | -------------------------- |
| Server                  | AWS EC2 (Ubuntu 24.04 LTS) |
| CI/CD                   | GitHub Actions             |
| Container               | Docker                     |
| Container Orchestration | Docker Compose             |
| Image Registry          | Docker Hub                 |
| Reverse Proxy           | Nginx                      |

---

## CI/CD Workflow

1. `main` 브랜치에 코드가 Push되면 GitHub Actions가 실행됩니다.
2. Gradle을 이용하여 프로젝트를 빌드합니다.
3. Docker 이미지를 생성합니다.
4. 생성된 이미지를 Docker Hub에 Push합니다.
5. GitHub Actions가 EC2 서버에 SSH로 접속합니다.
6. EC2에서 최신 Docker 이미지를 Pull합니다.
7. `docker-compose up -d`를 실행하여 애플리케이션을 배포합니다.

---

## Docker

Docker를 이용하여 Spring Boot 애플리케이션을 컨테이너화하였습니다.
애플리케이션 실행 환경과 의존성을 이미지에 포함하여 동일한 환경에서 실행할 수 있도록 구성하였습니다.

---

## Docker Compose

Docker Compose를 이용하여 Spring Boot 애플리케이션과 Nginx를 하나의 서비스로 관리하도록 구성하였습니다.

```bash
docker-compose up -d
```

---

## Nginx

Nginx를 Reverse Proxy로 사용하여 외부 HTTP 요청을 Spring Boot 컨테이너(8080)로 전달하도록 구성하였습니다.

```text
Client
   │
   ▼
Nginx (80)
   │
   ▼
Spring Boot (8080)
```

---

## Environment Variables

민감한 정보(DB 정보, JWT Secret 등)는 GitHub Repository에 포함하지 않고 GitHub Secrets를 통해 관리하였습니다.

배포 과정에서 GitHub Actions가 EC2 서버에 `.env` 파일을 생성하여 애플리케이션이 환경 변수를 사용할 수 있도록 구성하였습니다.

> **Note**
>
> 현재는 도메인이 없어 HTTP 환경으로 배포하였습니다.
> 도메인 연결 후에는 Certbot 또는 AWS ACM 등을 이용하여 HTTPS를 적용할 수 있습니다.
