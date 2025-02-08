
# Steam Random Games App

## Description
The Steam Random Games App allows users to randomized a game, fetch and search for games from the Steam API. The app provides both a REST API and a web interface to interact with the game data. The game list is fetched and stored locally, providing offline access to the data. Users can search for games by name, ID, or index, and view the details through a simple web interface.

## Features
- Fetches a list of games from the Steam API.
- Allows searching for games by name, ID, or index via both a REST API and a web interface.
- Local storage of game data for offline use.
- Simple and intuitive web interface with navigation.

## Technology Stack
- **Backend**: Java, Spring Boot
- **Frontend**: HTML
- **API**: Steam API for fetching game data

## Files Overview
- **GameController.java**: Manages REST API endpoints for retrieving game details by name, ID, or index.
- **GameModel.java**: Defines the `GameModel` class that represents a game with ID, name, and description.
- **GameService.java**: Contains the logic for interacting with the Steam API and handling game data.
- **SteamAPIService.java**: Fetches game data from the Steam API.
- **ReadFromFile.java**: Utility class for reading data from a file.
- **WriteToFile.java**: Utility class for writing data to a file.
- **SteamRandomGamesController.java**: Manages routing for the web interface and navigation.
- **SteamRandomGamesApplication.java**: Main entry point for the Spring Boot application.

## Setup

### Prerequisites
1. Java 11 or later
2. Maven or Gradle (for dependency management)
3. Spring Boot

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/DanielRoldanCS/SteamRandomGamesApp.git
   ```

2. Navigate to the project directory:
   ```bash
   cd SteamRandomGamesApp
   ```

3. Build the project:
   ```bash
   ./mvnw clean install
   ```

4. Set up the API URL and key:
    - Create a `application.properties` file in the `src/main/resources` directory.
    - Add the following properties:
      ```properties
      api.url=https://api.steampowered.com/IStoreService/GetAppList/v1/
      api.key=[YOUR-API-KEY-HERE]
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
- **Randomized Game**: `/randomized_game` - Displays random game from the list
### API Endpoints
- **GET /games**: Fetches the list of games from the Steam API and stores them locally.
- **GET /games/index/{index}**: Fetches a game by its index in the list.
- **GET /games/id/{id}**: Fetches a game by its ID.
- **GET /games/name/{name}**: Searches for games by name.
- **GET /games/random**: Fetches a random game.

### Exception Handling
- **GameNameNotFoundException**: Thrown when no games are found with the specified name.
- **IDNotFoundException**: Thrown when no game is found with the specified ID.

## Contributing
Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.
