package airport_departure_queuing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public String filepath;
    public String delimiter;
    private BufferedReader br;

    public CSVReader(String filepath) throws FileNotFoundException {
        this(filepath, ",");
    }

    public CSVReader(String filepath, String delimiter) throws FileNotFoundException {
        this.filepath = filepath;
        this.delimiter = delimiter;
        br = new BufferedReader(new FileReader(filepath));
    }

    public String[] readLine() throws IOException {
        String line = br.readLine();
        return line.split(delimiter);
    }
}
