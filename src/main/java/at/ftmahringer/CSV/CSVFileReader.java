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
    protected CSVReader reader;
    protected final File file;

    public CSVFileReader(String filename) {
        this.file = new File(filename);
        try {
            Reader inputfile = new FileReader(file);
            reader = new CSVReader(inputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] readNextLine() {
        try {
            return reader.readNext();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> readAllLines() {
        List<String[]> lines = new ArrayList<>();
        try {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public Integer size() {
        int size = 0;
        try {
            while (reader.readNext() != null) {
                size++;
            }
            // reset the reader to the beginning of the file
            reader.close();
            Reader inputfile = new FileReader(file);
            reader = new CSVReader(inputfile);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return size;
    }
}
