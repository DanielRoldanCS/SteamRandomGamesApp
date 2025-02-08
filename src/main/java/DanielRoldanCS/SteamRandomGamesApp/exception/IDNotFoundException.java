package DanielRoldanCS.SteamRandomGamesApp.exception;

/**
 * Exception thrown when a game with a specified ID is not found.
 */
public class IDNotFoundException extends Exception{
    //If an ID of a game searched for is not found
    public IDNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
