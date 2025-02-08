package DanielRoldanCS.SteamRandomGamesApp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class to read data from a file.
 */
public class ReadFromFile {
    private String fileName; // Path to the file to be read

    /**
     * Constructor to initialize the file name.
     *
     * @param fileName Name of the file to read from
     */
    public ReadFromFile(String fileName){
        this.fileName = fileName;
    }

    /**
     * Reads the content of the file and returns it as a string.
     *
     * @return File content as a string
     */
    public String read(){
        StringBuilder contentBuilder = new StringBuilder();

        try {
            //FileReader object with the specified path
            FileReader fileReader = new FileReader(this.fileName);

            //wrap the fileRreader in a bufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Read each line of the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }

            //Close the bufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.err.println("An error occured while reading the file: " + e.getMessage());
            e.printStackTrace();
        }

        //Return contents as string
        return contentBuilder.toString();
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
