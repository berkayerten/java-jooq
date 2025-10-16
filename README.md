# Java JOOQ Example

A sample Java application using JOOQ, Flyway, PostgreSQL, and Gradle.

## Features

- Java 21
- JOOQ for type-safe SQL
- Flyway for database migrations
- PostgreSQL database
- Testcontainers for integration testing
- Spring Boot 3.5.6

## Getting Started

### Prerequisites

- Java 21+
- Docker (for Testcontainers)
- PostgreSQL running on `localhost:5423` with:
  - Database: `mydb`
  - User: `myuser`
  - Password: `mypass`
  - Schema: `java_jooq`

### Setup

1. Clone the repository.
2. Update `resources/application.yml` and `build.gradle` if your database settings differ.
3. Run Flyway migrations:

## Project Structure

- `src/main/java/com/techthrivecatalyst/javajooq/` — Source code
- `src/test/java/com/techthrivecatalyst/javajooq/` — Integration tests
- `build/generated-src/jooq/main/` — JOOQ generated classes
