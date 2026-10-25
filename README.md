# Language Learning Platform

A Spring Boot backend (REST API) for a language learning platform: users, languages, learning materials, proficiency assessments, challenges and progress tracking.

## Features (as implemented in the controllers)

| Area | Base path | Endpoints |
|------|-----------|-----------|
| Users | `/users` | `POST /register`, `POST /login`, `POST /update` |
| Languages | `/languages` | `GET /supported`, `POST /add` |
| Learning materials | `/learning-materials` | `GET /{languageId}` |
| Proficiency assessments | `/proficiency-assessments` | `GET /{languageId}`, `POST /evaluate` |
| Language challenges | `/language-challenges` | `POST /create`, `POST /participate` |
| Progress tracking | `/progress-tracking` | `GET /{userId}/{languageId}`, `POST /record` |

Domain entities: `User`, `Language`, `LearningMaterial`, `ProficiencyAssessment`, `LanguageChallenge`, `ProgressTracking`.

## Tech stack

- Java 17, Maven (wrapper included)
- Spring Boot 3.2.5: Web, Security, Data JPA, Thymeleaf (with Spring Security extras)
- Lombok

## Project structure

```
src/main/java/com/languagelearningplatform/app/
  controllers/   REST controllers
  services/      service interfaces, impl/ implementations
  repository/    Spring Data repositories
  model/         JPA entities
  dto/           request/response DTOs
src/main/resources/application.properties
```

## Prerequisites

- JDK 17

## Setup and run

```bash
./mvnw spring-boot:run     # Windows: mvnw.cmd spring-boot:run
./mvnw test
```

## Configuration

`application.properties` currently only sets `spring.application.name=app`. The project uses Spring Data JPA, but no datasource is configured and no database driver is declared in `pom.xml`, so you will need to add a driver and datasource settings (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) for your own database.

## Status

This looks like an early-stage project. Note that `pom.xml` places a `dependencyManagement` block inside `dependencies` and pins `hibernate-core` 5.2.2.Final, which may need fixing before the project builds cleanly.
