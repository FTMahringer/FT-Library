package at.ftmahringer.CSV;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    // The CSVWriter object that writes to the CSV file
    protected final CSVWriter writer;
    // The delimiter used to separate values in the CSV file
    protected final String splitBy;
    // The file object that represents the CSV file
    protected final File file;

    /**
     * Constructor that initializes the CSVFileWriter with a file name, delimiter, and append mode
     * @param filename the name of the CSV file
     * @param splitBy the delimiter used to separate values in the CSV file
     * @param append whether to append to the file or overwrite it
     */
    public CSVFileWriter(String filename, String splitBy, boolean append) {
        this.splitBy = splitBy;
        this.file = new File(filename);

        try {
            // Create a FileWriter to write to the file
            FileWriter outputfile = new FileWriter(file, append);
            // Create a CSVWriter to write to the CSV file
            writer = new CSVWriter(outputfile);
        } catch (IOException e) {
            // Wrap the IOException in a RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Write a single line of data to the CSV file
     * @param data an array of strings representing the data to write
     */
    public void write(String[] data) {
        // Write the data to the CSV file
        writer.writeNext(data);
    }

    /**
     * Close the CSVWriter and release system resources
     */
    public void close() {
        try {
            // Close the CSVWriter
            writer.close();
        } catch (IOException e) {
            // Wrap the IOException in a RuntimeException
            throw new RuntimeException(e);
        }
    }
}
