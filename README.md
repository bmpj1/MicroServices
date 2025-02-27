# MicroServices

In this README, we'll explore the important concepts and the structure of spring boot projects. The project is organized into several packages, each serving a specific purpose.

## 1. Software and Versions
### Versioning:
- **Git:** A version control system.
- **GitKraken:** A Git GUI client for easy version control.
- 
### Database:
- `MongoDB Compass 1.40.4:`.

### Backend:
- `Java 17.0.9:` The programming language for backend development.
- `Maven 3.9.0:` A build tool for managing Java dependencies.
- `Spring Boot 3.1.5:` Provides project structure management for your backend.
- `Swagger:` For create a contract with the front and generate the API endpoints with documentation.
- `Mockito:` For unit testing.
- `Jacoco:` For coverage testing.
- `Postman:` Used for API testing.
- `Docker:`

### Commands:
- ```mvn clean verify```  : For test coverage with jacoco.
- ```

## Project Structure

### `controller`
The `controller` package contains the API endpoints of the application. Controllers define the RESTful endpoints and handle HTTP requests.

### `dto`
DTO (Data Transfer Object) classes in the `dto` package are used to transfer data between the client and server. They define the structure of data to be sent or received by the API.

### `module`
Modules represent various components or features of the application.

### `repository`
The `repository` package contains interfaces that extend the `MongoRepository` interface. These interfaces are used to perform CRUD operations on the database. For example, the `PostRepository` interface handles post-related operations.

### `service`
The `service` package contains service classes that handle the business logic of the application. Here is where the runtime exceptions are thrown. This is done due to the separation of concerns principle and to keep the code clean.
