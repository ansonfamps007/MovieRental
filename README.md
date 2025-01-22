# Movie Rental System


### Fetures

1. **Movie Rental Management**

* Support for renting movies with flexible rental periods.

* Calculate charges based on movie categories (Regular, New Release, Children).

2. **Rental Statements**

* Generate rental statements, including total charges and earned reward points.

3. **Error Handling**

* Handle missing movie records with descriptive error messages or default values.

### Technologies Used

* Java 17 - modern features such as record class (can avoid boilerplate code such as setters, getters and hasCode etc) , pattern matching and better perfomance with long term support

* Spring Boot 3.3 - preferred spring boot for rapid development, dependency management and include junit 

* JUnit 5 - for unit testing

* Gradle - for dependency management

## Installation and Setup

1. **Clone the Repository**

   ```bash
   git clone <repository-url>
   cd MovieRental
2. **Build the Project**

   ```bash
   ./gradlew build
3. **Run the Application**

   ```bash
   ./gradlew bootRun
4. **Access the APIs**

   ```bash
   http://localhost:8080/swagger-ui/index.html

```bash
 # Input
 
{
    "name": "C. U. Stomer",
    "rentals": [
        { "movieId": "F001", "days": 3 },
        { "movieId": "F002", "days": 1 },
        { "movieId": "F004", "days": 2 }
    ]
}

# Expected Output

Rental Record for C. U. Stomer:
	You've Got Mail	3.5
	Matrix	2.0
	Fast & Furious X	6.0
Amount owed is 11.5
You earned 3 frequent points
```
## Testing 
   ```bash
   ./gradlew test
```
### Further Improvement Suggestions
* Replace the static MovieDAO with a database-backed implementation
* DAO Layer Test Cases - TODO
* Controller Layer Test Cases - TODO
* Currently, only service layer test cases are added to test the main functionality of the application