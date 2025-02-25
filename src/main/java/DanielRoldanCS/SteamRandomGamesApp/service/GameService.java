package DanielRoldanCS.SteamRandomGamesApp.service;

import DanielRoldanCS.SteamRandomGamesApp.model.GameModel;
import DanielRoldanCS.SteamRandomGamesApp.util.ReadFromFile;
import DanielRoldanCS.SteamRandomGamesApp.util.WriteToFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Service class responsible for managing game data operations.
 */
@Service
public class GameService {

    @Autowired
    private SteamAPIService steamAPIService;
    private final ReadFromFile readFromFile = new ReadFromFile("ListOfSteamGames.json");


    /**
     * Fetches game data from the Steam API, stores it locally, and updates the game list.
     *
     * @return JSON string of stored games
     * @throws IOException if an error occurs during the API request
     */
    public String fetchAndStoreGames() throws IOException {

        //Calls the Steam API and stores the results in a String
        String storedSteamGames = steamAPIService.fetchSteamGamesFromAPI();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(storedSteamGames);

        //Convert the apps array back to a JSON string
        String appsJson = rootNode.toPrettyString();

        //Locally saves the result of the API
        WriteToFile writeToFile = new WriteToFile(appsJson, "ListOfSteamGames.json");
        writeToFile.write();

        //Gets the information from the file locally and displays it
        System.out.println(appsJson);
        return appsJson;
    }

    /**
     * Retrieves all stored games.
     *
     * @return List of all games
     */
    public List<GameModel> getAllGames() {
        // Gets the information from the file locally
        String storedSteamGames = readFromFile.read();

        //Parse JSON array
        JSONArray gamesArray = new JSONArray(storedSteamGames);

        //Variable will store the game objects which will contain the game information
        List<GameModel> listOfGames = new ArrayList<>();

        //Add each game object to the list
        for (int i = 0; i < gamesArray.length(); i++) {
            GameModel gameModel = new GameModel();
            JSONObject steamGameObject = gamesArray.getJSONObject(i);
            gameModel.setId(steamGameObject.getLong("appid"));
            gameModel.setName(steamGameObject.getString("name"));
            listOfGames.add(gameModel);
        }

        return listOfGames;
    }

    /**
     * Retrieves a game by its Index.
     *
     * @param index The list index
     * @return The game with the specified index
     */
    public String getGameByIndex(int index) {
        List<GameModel> listOfGames = getAllGames();

        //If the index is out of bounds will return corresponding error.
        if (index < 0 || index > listOfGames.size()) {
            throw new IndexOutOfBoundsException("Index specified is out of bounds");
        }

        //Try to get the index of the list of games, if available, will return the game ID and the game name
        return ("ID: " + listOfGames.get(index).getId() + " Name: " + listOfGames.get(index).getName());
    }

    /**
     * Retrieves a game by its ID.
     *
     * @param id The game ID
     * @return The game with the specified ID
     */
    public String getGameById(long id) {

        List<GameModel> listOfGames = getAllGames();

        //Stores the game ID and the game name
        HashMap<Long, String> games = new HashMap<>();

        //Adds each game ID and game name to a hashmap
        for (GameModel game : listOfGames) {
            games.put(game.getId(), game.getName());
        }

        //Returns the game name if found, if not found then it will return empty
        return games.get(id);
    }

    /**
     * Retrieves a game by its name.
     *
     * @param name The game name
     * @return The game with the specified name
     * @throws GameNameNotFoundException if no game is found
     */
    public List<GameModel> getGameByName(String name) {
        //Contains the list of games
        List<GameModel> listOfGames = getAllGames();

        //Contains the list of games found by name
        List<GameModel> gamesFound = new ArrayList<>();

        //Trimming spaces and converting to lowercase
        String[] searchWords = name.toLowerCase().split("\\s+");

        for (GameModel game : listOfGames) {
            String gameName = game.getName().toLowerCase();

            // Check if all words in the search query are present in the game name
            boolean allWordsMatch = true;
            for (String word : searchWords) {
                if (!gameName.contains(word)) {
                    allWordsMatch = false;
                    break;
                }
            }

            if (allWordsMatch) {
                gamesFound.add(game);
            }
        }

        return gamesFound;
    }

    /**
     * Retrieves a random game from the stored games list.
     *
     * @return A randomly selected game
     */
    public GameModel getRandomGame() {
        List<GameModel> games = getAllGames();
        if (games.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return games.get(random.nextInt(games.size()));
    }


}
