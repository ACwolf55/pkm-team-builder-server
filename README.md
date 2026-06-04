# Pokémon Team Builder — Spring Boot Server

REST API backend for the Pokémon Team Builder full-stack app. Handles user registration, JWT authentication, and full CRUD on Pokémon teams. Deployed on AWS Elastic Beanstalk with PostgreSQL on Supabase.

> **Live API:** http://pkm-team-builder-env.eba-fkwsgg63.us-west-2.elasticbeanstalk.com  
> **Frontend:** [pkmteambuilder.xyz](https://pkmteambuilder.xyz)

## Tech Stack

| Layer | Tech |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2 |
| Build | Maven |
| Auth | JWT (jjwt 0.11.5) |
| ORM | Spring Data JPA / Hibernate |
| Database | PostgreSQL (Supabase) |
| Hosting | AWS Elastic Beanstalk (single instance) |

## Architecture

```
src/main/java/com/example/
├── controller/
│   ├── TrainerController.java      # register, login, session
│   └── PokemonTeamController.java  # CRUD on teams
├── service/
│   ├── TrainerService.java
│   └── PokemonTeamService.java
├── entity/
│   ├── Trainer.java                # user model
│   └── PokemonTeam.java            # team model (stores pokemon list as JSON)
├── repository/
│   ├── TrainerRepository.java
│   └── PokemonTeamRepository.java
├── security/
│   ├── JwtUtil.java                # token generation + validation
│   ├── JwtFilter.java              # request filter
│   ├── SecurityConfig.java         # Spring Security config
│   └── TrainerDetailsService.java
└── WebConfig.java                  # CORS config
```

## API Endpoints

| Method | Route | Auth | Description |
|---|---|---|---|
| POST | /auth/register | No | Register new trainer |
| POST | /auth/login | No | Login, returns JWT |
| GET | /teams | Yes | Get all teams for logged-in trainer |
| POST | /teams | Yes | Save a new team |
| GET | /teams/{id} | Yes | Get a specific team |
| DELETE | /teams/{id} | Yes | Delete a team |

## Environment

`src/main/resources/application.properties` — local dev  
`src/main/resources/application-prod.properties` — AWS/production

Key env vars (set in AWS EB environment or locally):
```
SPRING_DATASOURCE_URL=jdbc:postgresql://...
SPRING_DATASOURCE_USERNAME=...
SPRING_DATASOURCE_PASSWORD=...
JWT_SECRET=...
```

## Deployment

Deployed to AWS Elastic Beanstalk (us-west-2, single instance, free tier).

**Notable production issue solved:** AWS EB environment is IPv4-only. Supabase's direct database host is IPv6-only. Fixed by switching to Supabase's **session pooler** connection string (IPv4 compatible) in production properties.

## Remaining Work

- [ ] Edit team endpoint (PUT /teams/{id})
- [ ] Team ownership authorization — verify JWT trainerId matches team's trainerId before returning data
- [ ] Catch IllegalArgumentException in TrainerController → return 400 (currently returns 403)
- [ ] Point domain (pkmteambuilder.xyz) at AWS via Cloudflare proxy for HTTPS
- [ ] Bump password minimum length (currently 4)
