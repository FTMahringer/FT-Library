package at.ftmahringer.CSV;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    protected final CSVWriter writer;
    protected final String splitBy;
    protected final File file;

    public CSVFileWriter(String filename, String splitBy, boolean append) {
        this.splitBy = splitBy;
        this.file = new File(filename);

        try {
            FileWriter outputfile = new FileWriter(file, append);
            writer = new CSVWriter(outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String[] data) {
        writer.writeNext(data);
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
