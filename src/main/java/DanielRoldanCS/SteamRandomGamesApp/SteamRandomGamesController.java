package DanielRoldanCS.SteamRandomGamesApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class to handle routing for different pages.
 */

@Controller
public class SteamRandomGamesController {

    /**
     * Maps to the home page.
     *
     * @return View name for home page
     */
    @GetMapping("/index")
    public String goHome(){
        return "index";
    }

    /**
     * Maps to the update page.
     *
     * @return View name for update page
     */
    @GetMapping("/update")
    public String goUpdate(){
        return "update";
    }

    /**
     * Maps to the search by index page.
     *
     * @return View name for search by index page
     */
    @GetMapping("/search_index")
    public String goSearchIndex()
    {
        return "search_index";
    }

    /**
     * Maps to the search by ID page.
     *
     * @return View name for search by ID page
     */
    @GetMapping("/search_id")
    public String goSearchId()
    {
        return "search_id";
    }

    /**
     * Maps to the search by name page.
     *
     * @return View name for search by name page
     */
    @GetMapping("/search_name")
    public String goSearchName()
    {
        return "search_name";
    }

    /**
     * Maps to the Randomized Game page.
     *
     * @return View name for Randomized Game page
     */
    @GetMapping("/randomized_game")
    public String goRandomizedGame()
    {
        return "randomized_game";
    }
}
