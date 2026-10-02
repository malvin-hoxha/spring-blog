# Spring Blog

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue.svg)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/Auth-JWT-black.svg)](https://jwt.io/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg)](https://docs.docker.com/compose/)

A RESTful blog backend built with **Spring Boot**, **PostgreSQL**, and **JWT-based authentication**. The project applies core backend engineering patterns — layered architecture, DTO-based API design, relational data modeling, and secured endpoints — to a domain (posts, categories, tags, users) that's small enough to be fully understood end-to-end, but rich enough to cover the fundamentals of a production-style Spring service.

---

## Features

**Authentication & Security**
- User registration and login
- JWT-based authentication via a custom security filter
- Password hashing and credential handling through Spring Security
- Role-based access control on protected endpoints

**Blog Management**
- CRUD operations for blog posts
- Post authorship (linked to the authenticated user)
- Category and tag management
- Relational associations between posts, authors, categories, and tags

**API Design**
- DTOs for all request/response payloads (no entity leakage)
- Request validation with Jakarta Bean Validation
- Entity ↔ DTO mapping via MapStruct
- Clear separation between controller, service, and repository layers

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.1.1 (Web MVC, Data JPA, Security, Validation) |
| Auth | JWT (`jjwt`) via a custom authentication filter |
| Database | PostgreSQL (runtime), H2 (tests) |
| ORM | Hibernate / Spring Data JPA |
| Mapping | MapStruct |
| Boilerplate | Lombok |
| Build | Maven (Maven Wrapper included) |
| Local infra | Docker Compose |

---

## Architecture

```text
Client
  │
  ▼
Spring Security filter chain (JWT validation)
  │
  ▼
Controllers        — HTTP boundary, request/response shaping
  │
  ▼
Services           — business logic
  │
  ▼
Repositories        — Spring Data JPA
  │
  ▼
PostgreSQL
```

Requests and responses never expose JPA entities directly — controllers and services operate on DTOs, which are mapped to/from entities with MapStruct. This keeps the persistence model free to evolve without breaking the public API contract.

### Domain model

```text
User
 └── Posts

Post
 ├── Author   → User
 ├── Category → Category
 └── Tags     → Tag

Category
 └── Posts

Tag
 └── Posts
```

---

## Project Structure

```text
spring-blog/
├── src/
│   ├── main/
│   │   ├── java/com/malvin/spring_blog/
│   │   │   ├── config/            # Spring configuration, beans
│   │   │   ├── controllers/       # REST controllers
│   │   │   ├── domain/
│   │   │   │   ├── dtos/          # Request / response DTOs
│   │   │   │   └── entities/      # JPA entities
│   │   │   ├── mappers/           # MapStruct mappers
│   │   │   ├── repositories/      # Spring Data JPA repositories
│   │   │   ├── security/          # JWT filter, security config
│   │   │   └── services/
│   │   │       └── impl/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── docker-compose.yml
├── pom.xml
├── mvnw / mvnw.cmd
```

---

## Getting Started

### Prerequisites
- JDK 17+
- Docker & Docker Compose
- (Optional) Maven — the Maven Wrapper is included

### 1. Clone the repository

```bash
git clone https://github.com/malvin-hoxha/spring-blog.git
cd spring-blog
```

### 2. Configure environment variables

Create a `.env` file in the project root:

```env
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_DB=
JWT_SECRET=
```

These are consumed by Docker Compose and by Spring Boot for the datasource and JWT signing key.

### 3. Start PostgreSQL

```bash
docker compose up -d
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API is available at `http://localhost:8080`.

---

## Testing

```bash
./mvnw test
```

Tests run against an in-memory **H2** database, isolated from the PostgreSQL runtime configuration.

---

## API Overview

> Illustrative — match these against the actual `@RequestMapping` paths in the controllers before publishing.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate and receive a JWT |
| `GET` | `/api/posts` | List posts |
| `POST` | `/api/posts` | Create a post (authenticated) |
| `PUT` | `/api/posts/{id}` | Update a post |
| `DELETE` | `/api/posts/{id}` | Delete a post |
| `GET` | `/api/categories` | List categories |
| `GET` | `/api/tags` | List tags |

---

## What This Project Demonstrates

- Layered Spring Boot architecture (Controller → Service → Repository)
- JWT authentication implemented from scratch with a custom security filter
- DTO ↔ entity mapping with MapStruct instead of manual conversion
- Relational modeling with `@ManyToOne` / `@OneToMany` associations
- Request validation with Jakarta Bean Validation
- Environment-based configuration (no hardcoded credentials)
- Dockerized local database for a reproducible dev setup

---

## License

No license specified yet.