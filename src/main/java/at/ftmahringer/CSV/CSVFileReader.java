package at.ftmahringer.CSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
    // The CSVReader object that reads the CSV file
    protected CSVReader reader;
    // The file object that represents the CSV file
    protected final File file;

    /**
     * Constructor that initializes the CSVFileReader with a file name
     * @param filename the name of the CSV file
     */
    public CSVFileReader(String filename) {
        this.file = new File(filename);
        try {
            // Create a FileReader to read the file
            Reader inputfile = new FileReader(file);
            // Create a CSVReader to parse the CSV file
            reader = new CSVReader(inputfile);
        } catch (IOException e) {
            // Wrap the IOException in a RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Close the CSVReader and release system resources
     */
    public void close() {
        try {
            // Close the CSVReader
            reader.close();
        } catch (IOException e) {
            // Wrap the IOException in a RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Read the next line from the CSV file
     * @return an array of strings representing the next line
     */
    public String[] readNextLine() {
        try {
            // Read the next line from the CSV file
            return reader.readNext();
        } catch (IOException | CsvException e) {
            // Wrap the exception in a RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Read all lines from the CSV file
     * @return a list of string arrays representing all lines
     */
    public List<String[]> readAllLines() {
        List<String[]> lines = new ArrayList<>();
        try {
            String[] nextLine;
            // Read all lines from the CSV file
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);
            }
        } catch (IOException | CsvException e) {
            // Wrap the exception in a RuntimeException
            throw new RuntimeException(e);
        }
        return lines;
    }

    /**
     * Get the number of lines in the CSV file
     * @return the number of lines
     */
    public Integer size() {
        int size = 0;
        try {
            // Read all lines from the CSV file to count them
            while (reader.readNext() != null) {
                size++;
            }
            // Reset the reader to the beginning of the file
            reader.close();
            Reader inputfile = new FileReader(file);
            reader = new CSVReader(inputfile);
        } catch (IOException | CsvException e) {
            // Wrap the exception in a RuntimeException
            throw new RuntimeException(e);
        }
        return size;
    }
}