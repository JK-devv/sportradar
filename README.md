### Sportradar coding exercise 
When the application is running, it reads the incoming file - input.json, parses the read information into the Event entity

#### Functionality
- At the request of the user "/info/winner" - returns 10 likely match results with additional information
- The user can also specify how many results they want to get with "/info/winner?count=UserValue"
- On request "info/teams" - you can get a list of all teams in alphabetical order (unique)

#### Project structure:
- Model
- Service
- Controller

#### Steps required to get started:
- Clone the project 
- Open at Intellij Idea
- Run app
- Send request on "http://localhost:8080/swagger-ui/"

####Technologies
- Java 11
- SpringBoot
- JUnit
- Swagger