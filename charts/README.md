# 📦 loyalty-chart

A Helm chart to deploy the **Loyalty** Spring Boot 3.4 service on Kubernetes.  
Supports PostgreSQL as database and integrates with Keycloak for authentication.

---

## 🛠 Installation

**Add the Helm repository** (replace with your actual registry if different):

```bash
helm repo add sepidan https://reg.sepidan.net/helm
helm repo update
```
## ⚙️ Configuration

This chart uses:
- `ConfigMap` for non-sensitive environment variables
- `Secret` for sensitive data like database password and Keycloak client secret

You can override these values with `--set` or by editing `values.yaml`.

### 📌 Main parameters

| Parameter                | Type   | Default                                        | Description                        |
|-------------------------:|:------:|:----------------------------------------------:|-----------------------------------|
| `replicaCount`           | int    | `1`                                            | Number of application pods         |
| `image.repository`       | string | `"reg.sepidan.net/sepidan/loyalty"`           | Docker image repository            |
| `image.tag`              | string | `"latest"`                                     | Docker image tag                   |
| `image.pullPolicy`       | string | `IfNotPresent`                                 | Image pull policy                  |
| `imagePullSecrets`       | list   | `- name: reg-sepidan-cred`                     | Credentials for private registry   |
| `service.type`           | string | `"ClusterIP"`                                  | Service type                      |
| `service.port`           | int    | `9098`                                         | Application port                  |

---

### 🌱 Environment variables (non-secret)

| Key                          | Type   | Default                                              | Description                      |
|-----------------------------:|:------:|:----------------------------------------------------:|---------------------------------|
| `env.TOMCAT_PORT`           | string | `"9098"`                                            | Tomcat server port               |
| `env.KEYCLOAK_URL`          | string | `"https://sso.sepidan.net"`                         | Keycloak server URL              |
| `env.KEYCLOAK_REALM`        | string | `"sepidan"`                                         | Keycloak realm                   |
| `env.KEYCLOAK_CLIENT_ID`    | string | `"loyalty"`                                         | Keycloak client ID               |
| `env.POSTGRES_HOST`         | string | `"postgres-headless.postgres.svc.cluster.local"`    | PostgreSQL host                  |
| `env.POSTGRES_DATABASE`     | string | `"loyalty"`                                         | PostgreSQL database name         |
| `env.POSTGRES_USER`         | string | `"loyalty_user"`                                    | PostgreSQL username              |
| `env.SPRING_PROFILES_ACTIVE`| string | `"dev"`                                             | Active Spring profile            |

---

### 🔒 Secrets

| Key                          | Type   | Description                      |
|-----------------------------:|:------:|---------------------------------|
| `secret.POSTGRES_PASSWORD`   | string | PostgreSQL password              |
| `secret.KEYCLOAK_CLIENT_SECRET` | string | Keycloak client secret        |
