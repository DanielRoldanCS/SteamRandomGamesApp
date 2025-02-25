package DanielRoldanCS.SteamRandomGamesApp.exception;

/**
 * Exception thrown when a game with a specified name is not found.
 */
public class GameNameNotFoundException extends Exception{
    //If a name of a game searched for is not found
    public GameNameNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
