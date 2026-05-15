# Pokémon Team Builder — Spring Boot Server

REST API backend for the [Pokémon Team Builder](https://pokemon-team-builder-hooks.vercel.app) full-stack app. Handles user accounts, authentication, and team CRUD. Built in Java with Spring Boot and JWT-based auth.

> **Live frontend:** [pokemon-team-builder-hooks.vercel.app](https://pokemon-team-builder-hooks.vercel.app)
> **Frontend repo:** [Pokemon-team-builder-hooks](https://github.com/ACwolf55/Pokemon-team-builder-hooks)

## Project History

Pokémon Team Builder started as my capstone project at **DevMountain bootcamp** — originally built with React class components, a Node/Express backend, and deployed to Heroku.

After three years teaching full-stack development at DevMountain and completing **Revature's Java / Spring Boot training program**, I'm rebuilding the project from the ground up to reflect what I've learned since:

- **Frontend:** migrating React from class components to functional components with hooks (see the [frontend repo](https://github.com/ACwolf55/Pokemon-team-builder-hooks))
- **Backend:** rebuilt in Java + Spring Boot (this repo) to deepen enterprise / JVM skills
- **Deployment:** moving from Heroku to AWS Elastic Beanstalk

Same product, modern stack.

## Tech Stack

| Layer | Tech |
|---|---|
| Language / Runtime | Java 20 |
| Framework | Spring Boot 3.2 |
| Security | Spring Security + JWT (jjwt 0.11.5), BCrypt hashing |
| Data | Spring Data JPA / Hibernate, PostgreSQL |
| Build | Maven |
| Monitoring | Spring Actuator |

## Architecture

Standard Spring layered architecture:

```
Controller → Service → Repository → Entity → Database
                ↑
        SecurityConfig + JwtFilter
        (intercepts protected routes)
```

- **`controller/`** — REST endpoints (`TrainerController`, `PokemonTeamController`)
- **`service/`** — business logic, transaction boundaries
- **`repository/`** — JPA repositories (data access)
- **`entity/`** — JPA entities (`Trainer`, `PokemonTeam`)
- **`security/`** — JWT filter, JWT utility, password encoding, `SecurityConfig`

## API Endpoints

### Authentication (`/auth/**` — public)

| Method | Path | Body | Returns |
|---|---|---|---|
| POST | `/auth/register` | `{ trainerName, password }` | Trainer (201) or 409 conflict |
| POST | `/auth/login` | `{ trainerName, password }` | `{ token, trainerId, trainerName }` (200) or 401 |

### Teams (`/pokemon-teams/**` — JWT required)

| Method | Path | Returns |
|---|---|---|
| POST | `/pokemon-teams/pokemon_team` | Created team |
| GET | `/pokemon-teams/pokemon_team/team_id/{id}` | Single team |
| GET | `/pokemon-teams/pokemon_team/{trainer_id}` | All teams for a trainer |
| DELETE | `/pokemon-teams/pokemon_team/{id}` | Row count affected |

Authenticated requests must include `Authorization: Bearer <token>`.

## Authentication Flow

1. Client `POST`s credentials to `/auth/login`.
2. Server verifies the password with BCrypt and generates a JWT signed with the configured secret.
3. Client stores the token and attaches it to every subsequent request as `Authorization: Bearer <token>`.
4. `JwtFilter` intercepts each request, validates the token, and populates Spring's `SecurityContext` before reaching protected endpoints.
5. Sessions are **stateless** — no server-side session storage.

## Getting Started

### Prerequisites
- Java 20+
- Maven 3.9+
- PostgreSQL (or use the H2 in-memory option included for testing)

### Configuration

Create `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pkm_team_builder
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-secret-key-here
```

### Run

```bash
mvn spring-boot:run
```

Server starts on `http://localhost:8080`.

## Roadmap

- [ ] Add `PUT /pokemon-teams/pokemon_team/{id}` for team editing (currently only create/read/delete)
- [ ] Deploy to AWS Elastic Beanstalk
- [ ] Profile-based CORS: `application-dev.properties` (allow all origins) vs `application-prod.properties` (restrict to Vercel domain)
- [ ] Refactor `PokemonTeam` entity to use a join table instead of six denormalized columns
- [ ] Add integration tests
- [ ] OpenAPI / Swagger docs

## What I Learned

- Spring Security's filter chain and how JWT integration plugs into it
- Stateless authentication patterns (no `HttpSession`, all state in the token)
- BCrypt password hashing and why salt matters
- JPA entity mapping and the trade-offs of denormalized vs. normalized schemas
- CORS configuration and how to scope it for different environments
- Differences between Spring Boot's enterprise patterns and the lighter Node/Express approach I used for the original
