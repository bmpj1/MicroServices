# MicroServices

In this README, we'll explore the important concepts and the structure of spring boot projects. The project is organized into several packages, each serving a specific purpose.

## 1. Software and Versions
### Versioning:
- **Git:** A version control system.
- **GitKraken:** A Git GUI client for easy version control.
- 
### Database:
- `MongoDB Compass 1.40.4:` MongoDB Compass is a GUI for visualizing, querying, and managing your MongoDB data.
- `Spring Data MongoDB:` For interacting with MongoDB.

### Backend:
- `Java 17.0.9:` The programming language for backend development.
- `Maven 3.9.0:` A build tool for managing Java dependencies.
- `Spring Boot 3.1.5:` Provides project structure management for your backend.
- `Lombok:` For reducing boilerplate code.
- `Spring Webflux:` For building reactive web applications.
- `Spring Validation:` For validating data.
- `Spring Boot Actuator:` For monitoring and managing the application.
  
#### Documentation and code generation:
- `Springdoc OpenAPI:` For generating Swagger UI at "http://localhost:8080/swagger-ui/index.html#/".
- `Swagger:` For create a contract with the front and generate the API endpoints with documentation.

#### Testing:
- `JUnit Jupiter:` For unit testing.
- `Mockito:` For mocking dependencies in unit tests.
- `Testcontainers:` For integration testing with Docker containers.
- `Jacoco:` For coverage testing reports.

#### Others
- `Postman:` Used for API testing.
- `Docker:` For building, deploying, and running applications in containers.

### Commands:
- ```mvn clean```  : Cleans the project by deleting the target directory.
- ```mvn test```  : Runs the unit tests of the project.
- ```mvn clean install```  : Installs the packages into the local Maven repository.
- ```mvn clean verify```  : For test coverage with jacoco.
- ```mvn spring-boot:run```  : Runs the Spring Boot application (requires the Spring Boot Maven plugin) at "http://localhost:8080/v1/...".

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

### `fachade`
The ProductFacade class acts as an intermediary to retrieve product data from an external API (dummyjson.com) and convert it into the internal ProductModel format. It provides methods for fetching all products, retrieving a product by ID, and searching for products using a search query.
