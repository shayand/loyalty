# Agent Guidelines for Loyalty Project

## Project Overview
This is a Spring Boot 3.4.6 loyalty management system built with Java 21, using Maven as the build tool. The project follows a domain-driven design pattern with clear separation of concerns.

## Build and Test Commands

### Core Maven Commands
```bash
# Clean and compile the project
./mvnw clean compile

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=LoyaltyApplicationTests

# Run tests with specific method
./mvnw test -Dtest=LoyaltyApplicationTests#contextLoads

# Package the application (creates JAR)
./mvnw clean package

# Skip tests during packaging
./mvnw clean package -DskipTests

# Run the application locally
./mvnw spring-boot:run

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Build Docker image with native compilation
./mvnw spring-boot:build-image -Pnative

# Compile native executable
./mvnw native:compile -Pnative
```

### Profiles
- `dev` (default): Development profile with tests enabled
- `prod`: Production profile with tests skipped (`skipTests=true`)

## Project Structure and Conventions

### Package Structure
```
net.sepidan.loyalty/
├── config/           # Configuration classes (Security, Web, Redis, etc.)
├── controller/       # REST API controllers extending BaseController
├── dto/             # Data Transfer Objects
│   └── mapper/      # MapStruct mappers for entity/dto conversion
├── exception/       # Custom exceptions and global exception handlers
├── persistent/      # Data persistence layer
│   ├── domain/      # JPA entities
│   ├── repository/  # Spring Data JPA repositories
│   └── service/     # Business logic services
├── annotation/      # Custom annotations
├── constant/        # Enums and constants
├── payload/         # Request/Response objects
│   └── request/    # Request DTOs
└── utils/           # Utility classes
```

### Code Style Guidelines

#### Import Organization
- Group imports: Java standard libraries, third-party libraries, project packages
- Use static imports sparingly, mainly for constants (e.g., `DateTimeConstants`)
- Third-party imports should be alphabetized within their groups

#### Naming Conventions
- **Classes**: PascalCase (e.g., `AffiliateService`, `AffiliatesRepository`)
- **Methods**: camelCase with descriptive names (e.g., `createAffiliate`, `getAffiliatesBalance`)
- **Variables**: camelCase (e.g., `affiliatesRepository`, `affiliateCreateRequest`)
- **Constants**: UPPER_SNAKE_CASE in dedicated constant classes
- **Entities**: Plural form (e.g., `Affiliates`, `Instances`, `Tiers`)
- **DTOs**: Descriptive suffix (e.g., `AffiliatesDto`, `AffiliatesBalanceDto`)

#### Entity Conventions
- Use JPA annotations with clear table mappings
- Include `@CreationTimestamp` and `@UpdateTimestamp` for audit fields
- Use `ZonedDateTime` for all date/time fields
- Include soft delete with `deletedAt` field
- Use `@JsonIgnore` and `@ToString.Exclude` for bidirectional relationships
- Generate sequence-based primary keys with custom generators

#### Service Layer Patterns
- Use `@Service` and `@AllArgsConstructor` for dependency injection
- Services should be stateless and focus on business logic
- Use repositories for data access, mappers for DTO conversion
- Handle domain-specific operations and orchestration
- Return DTOs from service methods, not entities

#### Controller Patterns
- Extend `BaseController` for consistency
- Use `@RestController` with appropriate `@RequestMapping`
- Return `ResponseEntity` for proper HTTP status handling
- Use `@Valid` for request body validation
- Include OpenAPI annotations (`@Tag`) for documentation
- Keep controllers thin, delegate to services

#### Exception Handling
- Create custom exceptions extending RuntimeException
- Use global exception handler with `@ControllerAdvice`
- Return appropriate HTTP status codes
- Provide meaningful error messages

#### Mapper Patterns
- Use MapStruct for entity/DTO conversion
- Configure with `componentModel = ComponentModel.SPRING`
- Use `unmappedTargetPolicy = ReportingPolicy.IGNORE`
- Include partial update methods with `@BeanMapping`

#### Testing
- Follow Spring Boot testing conventions
- Use `@SpringBootTest` for integration tests
- Test classes should be in `src/test/java` with same package structure
- Test method names should be descriptive and follow `methodName_condition_expectedResult` pattern

## Technology Stack

### Core Dependencies
- **Spring Boot 3.4.6** with Spring Web, Data JPA, Security
- **Java 21** with modern language features
- **PostgreSQL** as primary database
- **Liquibase** for database migrations
- **Keycloak** for authentication/authorization
- **MapStruct 1.6.3** for entity/DTO mapping
- **Lombok 1.18.36** for reducing boilerplate
- **SpringDoc OpenAPI** for API documentation
- **Redisson** for Redis integration

### Key Configuration
- Timezone: Asia/Tehran (configured in Maven properties)
- Encoding: UTF-8
- Transaction management enabled
- JPA repositories configured with custom repository base

## Development Notes

### Database Schema
- Uses sequence generators for primary keys
- Soft delete pattern with `deleted_at` timestamps
- Audit fields: `created_at`, `updated_at`
- Enum fields stored as strings (`EnumType.STRING`)

### Security
- OAuth2 Resource Server with JWT tokens
- Keycloak integration for identity management
- Public endpoints: `/`, `/api-docs/**`
- All other endpoints require authentication

### API Documentation
- OpenAPI/Swagger available at `/api-docs/**`
- Use `@Tag` annotations for controller grouping
- Include proper request/response documentation

### Performance Considerations
- Use `FetchType.LAZY` for JPA relationships
- `@JsonIgnore` to prevent infinite recursion
- Hibernate enhancement enabled for lazy loading and dirty tracking

## Common Patterns

### Repository Queries
- Custom query methods in repository interfaces
- Use `Optional<T>` for single result queries
- Follow Spring Data naming conventions

### Error Responses
- Use global exception handler for consistent error format
- Return HTTP 400 for client errors (like `ResourceNotFoundException`)
- Include meaningful error messages

### DTO Validation
- Use `@Valid` annotation in controllers
- Add validation annotations to request DTOs
- Handle validation errors through exception handlers

### Utility Classes
- Place reusable logic in `utils` package
- Use static methods for pure functions
- Include timezone handling utilities (TEHRAN_ZONE_ID)