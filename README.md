# Kalah game - service

This is the backend of Kalah game. https://en.wikipedia.org/wiki/Kalah  
Front end can be foud at: https://github.com/xlucasdemelo/kalah-angular

## Getting Started

### Prerequisites

This application uses maven for dependency management, it is mandatory to install it.

```
Java 8: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
Maven: https://maven.apache.org/download.cgi

```

### Running with maven

Clone the project

```
git clone https://github.com/xlucasdemelo/Kalah.git
```

to start the application:

```
mvn spring-boot:run
```

### Conception

Basically this application have two main Interfaces:  
- GameMove: A interface that will MOVE a seed from the board 
- GameRule: A interface that is used to validate a rule from the game

The state of the game is represented by the class Game.  
The Orchestrator class is responsible to apply the GameRules, the GameMoves and return the state of the game to the service

A Controller class exposes the interactions of the game, all the communication is done via DTOs  
A Service class is used to apply some logic to the DTO and call the Orchestrator, for future work this can be used to store data into the DB

### Running tests

Execute the following command:

```
mvn test
```

### Unit tests
-All GameRules and GameMoves have their own tests

### Integration tests
- Since i dont have any logic in service or controller, i did not wrote tests for these classes, the "integration tests" are being done via the Orchestrator class   


### Sample requests

The application have the following endpoints:

```
POST: http://localhost:8080/api/kalah/
GET: http://localhost:8080/api/kalah/
PATCH: http://localhost:8080/api/kalah/restartGame
```

1.Player one moves house number 1:

```
curl --header "Content-Type: application/json" --request POST --data '{ "playerName": "PLAYER ONE", "houseIndex": 0}'  http://localhost:8080/api/kalah/
```

2.Player two moves house number 9:

```
curl --header "Content-Type: application/json" --request POST --data '{ "playerName": "PLAYER TWO", "houseIndex": 8}'  http://localhost:8080/api/kalah/
```

3.Get the state of the game:

```
curl http://localhost:8080/api/kalah/
```

4.Restart the game:

```
curl http://localhost:8080/api/kalah/
```

*To check a example of response access the swagger UI at
```
http://localhost:8080/swagger-ui.html#/
```

### Documentation

I am using Swagger2 to provide the documentation for the application, it could be accessed at: 

```
http://localhost:8080/swagger-ui.html#/
```

### Improvements

As future works some improvements were identified:
- Persist the game data: Add a layer of persistence and persist every move, turn, decision, etc...
- logs: Improve the way application is logging its actions, create a log appender to log every unique transaction to achieve logs as data stream. https://12factor.net/logs
- Improve the UI