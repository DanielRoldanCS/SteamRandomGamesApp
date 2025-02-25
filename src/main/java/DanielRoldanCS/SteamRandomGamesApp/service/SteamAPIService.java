package DanielRoldanCS.SteamRandomGamesApp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Service class responsible for fetching game data from the Steam API.
 */
@Service
public class SteamAPIService {
    @Value("${api.url}") // Steam API base URL (configured in application properties)
    private String apiurl;
    @Value("${api.key}") // Steam API key (configured in application properties)
    private String apikey;

    /**
     * Fetches game data from the Steam API, handling pagination.
     * The API provides data in pages, requiring additional requests if more results are available.
     *
     * @return A complete JSON array of all games as a string
     * @throws IOException if an error occurs during the API request
     */
    public String fetchSteamGamesFromAPI() throws IOException {
        StringBuilder allGames = new StringBuilder("["); // Start JSON array
        long lastAppId = 0; // Track last processed app ID for pagination
        boolean firstPage = true; // Flag to manage JSON formatting

        ObjectMapper objectMapper = new ObjectMapper();

        while (true) { // Loop until there's no "last_appid"/ no more results.
            String apiUrlWithParams = apiurl + "?key=" + apikey;
            if (lastAppId > 0) {
                apiUrlWithParams += "&last_appid=" + lastAppId;
            }

            // Create and configure the HTTP request
            URL url = new URL(apiUrlWithParams);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Verify successful response
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            // Read API response
            String response = readAPIResponse(conn);

            // Parse JSON
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode appsArray = rootNode.path("response").path("apps");
            JsonNode lastAppIdNode = rootNode.path("response").path("last_appid");

            // Validate expected JSON structure
            if (!appsArray.isArray()) {
                throw new RuntimeException("Unexpected JSON structure from Steam API");
            }

            // Append game data (avoid leading comma issue for proper JSON formatting)
            if (!firstPage) {
                allGames.append(",");
            }
            firstPage = false;

            // Append extracted game data, removing surrounding brackets
            allGames.append(appsArray.toString().replaceAll("^\\[|\\]$", "")); // Remove array brackets

            // Check if we need to continue fetching more pages
            if (lastAppIdNode.isMissingNode()) {
                break;
            }
            lastAppId = lastAppIdNode.asLong(); // Update lastAppId for the next API call
            System.out.println("Fetching next page lastAppId: " + lastAppId);
        }

        allGames.append("]"); // Close JSON array
        return allGames.toString();
    }




    /**
     * Reads and returns the complete response from the API as a string.
     *
     * @param connection The active HTTP connection
     * @return The full API response as a string
     * @throws IOException if an error occurs while reading the response
     */
    private String readAPIResponse(HttpURLConnection connection) throws IOException {
        //Create a buffered reader to read the input stream from the HTTP Connection
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        //Holds the response
        String inputLine;
        StringBuilder response = new StringBuilder();

        //Reads each value and appends it to a String Builder
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        //Closes the connection
        in.close();

        return response.toString();

    }

}
