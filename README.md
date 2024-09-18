# Student Info API

A robust and scalable backend application built with Spring Boot for managing student information. This API provides comprehensive CRUD operations with proper exception handling and integrates with a MySQL database for storing and retrieving student data.

### Features

The following guides illustrate how to use some features concretely:

* CRUD Operations: Create, Read, Update, and Delete student records.
* Exception Handling: Graceful error handling with informative responses.
* Database Integration: Seamless integration with MySQL for persistent data storage.
* Unit Testing: Comprehensive test coverage for repository, service, and controller layers to ensure reliability and correctness.


### Technologies

* Spring Boot: Framework for building the backend application.
* Spring Data JPA: For interacting with the MySQL database.
* MySQL: Relational database for data storage.
* JUnit and Mockito: For unit testing and mocking dependencies.

## Getting Started
### Prerequisites

* Java 21
* Maven
* MySQL

### Setup
1. Clone the Repository:
```
git clone https://github.com/yourusername/student-info-api.git
```

2. Configure the Database: Update `src/main/resources/application.properties` with your MySQL database credentials:
```
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
3. Build and Run: Navigate to the project directory and run:
```
mvn clean install
mvn spring-boot:run
```

4. Access the API: The application will be available at `http://localhost:8080/api/students`.

### API Endpoints
* POST /api/students: Create a new student record.
* GET /api/students: Retrieve all student records.
* GET /api/students/{id}: Retrieve a student record by ID.
* PUT /api/students/{id}: Update a student record by ID.
* DELETE /api/students/{id}: Delete a student record by ID.

### Contributing
Contributions are welcome! Please follow the guidelines for contributing and submit a pull request for any improvements or bug fixes.

### License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.