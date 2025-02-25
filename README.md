# Steam Random Games App

## Description
The Steam Random Games App allows users to randomize a game, fetch and search for games from the Steam API. The app provides both a REST API and a web interface to interact with the game data. The game list is fetched and stored locally, providing offline access to the data. Users can search for games by name, ID, or index, and view the details through a simple web interface. Additionally, users can be managed through the app, including adding, searching, updating, and deleting users.

**Note:** This project is still in development. A front-end interface using Angular is currently being built to enhance user experience and connect with the backend more efficiently.

## Features
- Fetches a list of games from the Steam API.
- Allows searching for games by name, ID, or index via both a REST API and a web interface.
- Local storage of game data for offline use.
- Simple and intuitive web interface with navigation.
- User management: add, update, delete, and search users by name or email.

## Technology Stack
- **Backend**: Java, Spring Boot
- **Frontend**: HTML (current), Angular (in progress)
- **Database**: MySQL
- **API**: Steam API for fetching game data

## Development Status
- The core backend functionality is implemented and operational.
- The frontend is currently being transitioned to Angular for better performance and user experience.
- Additional features, including improved UI/UX and advanced filtering options, are planned for future updates.

## Files Overview
### Game Management
- **GameController.java**: Manages REST API endpoints for retrieving game details by name, ID, or index.
- **GameModel.java**: Defines the `GameModel` class that represents a game with ID, name, and description.
- **GameService.java**: Contains the logic for interacting with the Steam API and handling game data.
- **SteamAPIService.java**: Fetches game data from the Steam API.
- **ReadFromFile.java**: Utility class for reading data from a file.
- **WriteToFile.java**: Utility class for writing data to a file.
- **SteamRandomGamesController.java**: Manages routing for the web interface and navigation.
- **SteamRandomGamesApplication.java**: Main entry point for the Spring Boot application.

### User Management
- **UserController.java**: Handles REST API endpoints for user operations (CRUD and search).
- **UserService.java**: Implements user management logic, including searching by name or email.
- **User.java**: Defines the `User` model representing a user entity.
- **UserRepository.java**: Interface for database interactions for user storage and retrieval.

## Setup

### Prerequisites
1. Java 11 or later
2. Maven or Gradle (for dependency management)
3. Spring Boot
4. MySQL database

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/DanielRoldanCS/SteamRandomGamesApp.git
   ```

2. Navigate to the project directory:
   ```bash
   cd SteamRandomGamesApp
   ```

3. Configure the `application.properties` file:
   - Create a `application.properties` file in the `src/main/resources` directory.
   - Add the following properties:
     ```properties
     # Steam API Configuration
     api.url=https://api.steampowered.com/IStoreService/GetAppList/v1/
     api.key=[YOUR-API-KEY-HERE]

     # MySQL Database Configuration
     server.port=8080
     spring.datasource.url=jdbc:mysql://localhost:3306/[YOUR-DATABASE-NAME]
     spring.datasource.username=[YOUR-USERNAME]
     spring.datasource.password=[YOUR-PASSWORD]
     spring.jpa.show-sql=true
     spring.jpa.hibernate.ddl-auto=update
     ```

4. Build the project:
   ```bash
   ./mvnw clean install
   ```

5. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Web Interface
Once the application is running, you can access the following pages in your browser:

- **Home**: `/index` - Displays the homepage with buttons for different actions.
- **Update**: `/update` - Fetches and stores the list of games from the Steam API.
- **Search by Index**: `/search_index` - Allows searching for a game by its index in the list.
- **Search by ID**: `/search_id` - Allows searching for a game by its ID.
- **Search by Name**: `/search_name` - Allows searching for games by name.
- **Randomized Game**: `/randomized_game` - Displays a random game from the list.

### API Endpoints
#### Game API
- **GET /games**: Fetches the list of games from the Steam API and stores them locally.
- **GET /games/index/{index}**: Fetches a game by its index in the list.
- **GET /games/id/{id}**: Fetches a game by its ID.
- **GET /games/name/{name}**: Searches for games by name.
- **GET /games/random**: Fetches a random game.

#### User API
- **POST /api/users/add**: Adds a new user.
- **GET /api/users/all**: Retrieves all users.
- **GET /api/users/{id}**: Fetches a user by ID.
- **GET /api/users/search?email={email}**: Searches for users by email (case-insensitive).
- **GET /api/users/search?name={name}**: Searches for users by name (case-insensitive).
- **PUT /api/users/{id}**: Updates an existing user.
- **DELETE /api/users/{id}**: Deletes a user by ID.

### Exception Handling
- **GameNameNotFoundException**: Thrown when no games are found with the specified name.
- **IDNotFoundException**: Thrown when no game is found with the specified ID.
- **UserNotFoundException**: Thrown when no user is found with the specified ID, name, or email.

## Future Enhancements
- **Angular Frontend**: A new user-friendly UI is being developed using Angular to connect to the backend.
- **Advanced Filtering**: Options to filter games by genre, multiplayer support, and popularity.
- **Additional User Features**: More functionalities for user preferences and recommendations.

## Contributing
Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.
