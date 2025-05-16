# EazyBank Microservices - Developer Guidelines

## Project Structure and Tech Stack

### Overview
This project implements a microservices architecture for EazyBank, providing banking services through multiple independent microservices. The architecture follows Spring Cloud patterns for service discovery, configuration management, and inter-service communication.

### Project Structure

```
v2-spring-cloud-config/
├── accounts/                  # Accounts microservice
├── cards/                     # Cards microservice
├── loans/                     # Loans microservice
├── configserver/              # Centralized configuration server
├── eurekaserver/              # Service discovery server
└── docker-compose/            # Docker deployment configurations
    ├── default/               # Default environment configuration
    ├── qa/                    # QA environment configuration
    └── prod/                  # Production environment configuration
```

### Tech Stack

#### Core Technologies
- **Java 21**: Programming language
- **Spring Boot 3.4.x**: Application framework
- **Spring Cloud 2024.0.x**: Cloud-native patterns implementation
- **Maven**: Build and dependency management
- **Docker**: Containerization
- **MySQL**: Database

#### Microservices Components
- **Spring Cloud Config Server**: Centralized configuration management
- **Netflix Eureka**: Service discovery and registration
- **Spring Cloud OpenFeign**: Declarative REST client for service-to-service communication
- **Spring Data JPA**: Database access layer
- **Spring Boot Actuator**: Application monitoring and management
- **Springdoc OpenAPI**: API documentation

#### Development Tools
- **Lombok**: Reduces boilerplate code
- **Spring Boot DevTools**: Development-time productivity tools
- **JIB Maven Plugin**: Container image building

### Microservices Architecture

#### Configuration Management
- All services retrieve their configuration from the Config Server
- Config Server fetches configurations from a Git repository
- Environment-specific configurations are managed through Spring profiles
- Sensitive information can be encrypted using the Config Server's encryption key

#### Service Discovery
- All microservices register with Eureka Server
- Services discover and communicate with each other through Eureka
- Services are identified by their application name

#### Database Architecture
- Each microservice has its own dedicated database (Database per Service pattern)
- All databases are MySQL instances
- Database credentials are managed through configuration

#### API Documentation
- API documentation is generated using Springdoc OpenAPI
- Swagger UI is available at `/swagger-ui.html` for each service

### Development Workflow

#### Setting Up Development Environment
1. Clone the repository
2. Install JDK 21
3. Install Maven
4. Install Docker and Docker Compose
5. Import the project into your IDE

#### Running Locally
1. Start the Config Server first
2. Start the Eureka Server
3. Start the business microservices (accounts, loans, cards)

#### Running with Docker
1. Build the services using Maven: `mvn clean package`
2. Build Docker images using JIB: `mvn compile jib:dockerBuild`
3. Run the services using Docker Compose: `docker-compose -f docker-compose/default/docker-compose.yml up -d`

#### Making Changes
1. Make changes to the service code
2. Run tests to verify changes
3. Build and deploy the service
4. Update API documentation if endpoints have changed

### Testing Guidelines

#### Unit Testing
- Write unit tests for all business logic
- Use Spring Boot Test for integration testing
- Mock external dependencies using Mockito

#### Integration Testing
- Test API endpoints using RestTemplate or TestRestTemplate
- Verify database operations with @DataJpaTest
- Test service-to-service communication with WireMock

#### End-to-End Testing
- Use Postman collections for manual testing
- Automate API tests using the Postman collection runner

### Deployment Guidelines

#### Docker Deployment
- Each service is containerized using JIB Maven Plugin
- Services are deployed using Docker Compose
- Different environments (default, qa, prod) have separate Docker Compose configurations
- Health checks ensure services start in the correct order

#### Environment Configuration
- Use Spring profiles to manage environment-specific configurations
- Override configurations using environment variables in Docker Compose

#### Monitoring
- Use Spring Boot Actuator endpoints for monitoring
- Health endpoints are configured for Kubernetes readiness/liveness probes

### Best Practices

#### Code Style
- Follow standard Java code conventions
- Use Lombok annotations to reduce boilerplate
- Document public APIs with Javadoc

#### Microservices Design
- Keep services small and focused on a single business capability
- Design APIs for backward compatibility
- Use circuit breakers for resilience
- Implement proper error handling and logging

#### Security
- Secure sensitive configuration using encryption
- Implement proper authentication and authorization
- Validate all input data

#### Performance
- Optimize database queries
- Use connection pooling
- Configure appropriate JVM memory settings
- Monitor service performance using Actuator metrics