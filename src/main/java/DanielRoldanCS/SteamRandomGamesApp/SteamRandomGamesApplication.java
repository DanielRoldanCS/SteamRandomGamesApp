package DanielRoldanCS.SteamRandomGamesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main entry point for the Steam Random Games application.
 */
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class SteamRandomGamesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SteamRandomGamesApplication.class, args);

	}

}
