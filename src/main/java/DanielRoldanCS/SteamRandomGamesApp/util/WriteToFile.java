package DanielRoldanCS.SteamRandomGamesApp.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class to write data to a file.
 */
public class WriteToFile {

    private String data;
    private String fileName;

    /**
     * Constructor to initialize data and file name.
     *
     * @param data Data to be written to the file
     * @param fileName Name of the file where data will be saved
     */
    public WriteToFile(String data, String fileName) {
        this.data = data; // Content to be written to the file
        this.fileName = fileName; // Path to the file
    }

    /**
     * Writes the data to the specified file.
     */
    public void write() {

        try {
            //Create a filewriter object with a path
            FileWriter writer = new FileWriter(this.fileName);

            //write the content to the file
            writer.write(this.data);

            //Close the filewriter to release resources
            writer.close();

        } catch (IOException e) {
            //Handles the exception that might occur
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
