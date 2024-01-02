# Growth Diary Backend

## Overview

The backend RESTful API and server-side logic of the app was developed using Java and Spring Boot. The backend primarily uses the the Spring MVC architecture
of separating concerns, typically through using a `Controller`, `Service`, `Repository` and `Model`/`Entity`.

## File structure

The codebase is organised using a domain-driven approach. The bulk of code live here:

* `tracker`: Contains entities for user learning session and logic/validation for storing data
* `history`: Contains Spring JPA specifications and logic/validation to handle custom filters, pagination and sorting
* `analytics`: Contains Spring JPA projections and native queries to retrieve user learning stats and correlations
* `test`: Suite of unit and integration tests for each of the above features

## Getting started

To run the development server locally, kindly follow these steps:

1. Make sure you have Java and Maven installed
2. Clone the repository:
```
git clone https://github.com/ElijahQuiazon/GrowthDiary.git
```
3. Navigate to the frontend directory:
```
cd backend
```
4. Build the project
```
mvn clean install
```
5. Run the SessionLogApplication

The backend will start, and the API will be accessible at http://localhost:8080.

## Database
Growth Diary backend uses MySQL for data storage. Make sure to configure the database connection details in the application.properties file.

## Tests
Unit and integration tests mainly utilise the JUnit and Mockito frameworks.

## Potential areas for contribution/improvement
* Feel free to improve on the code, expand on it or suggest any areas for improvement