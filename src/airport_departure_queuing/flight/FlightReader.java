package airport_departure_queuing.flight;

import airport_departure_queuing.common.CSVReader;
import airport_departure_queuing.common.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class FlightReader extends CSVReader {
    public FlightReader(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    public FlightReader(String filepath, String delimiter) throws FileNotFoundException {
        super(filepath, delimiter);
    }

    public long getDate() throws IOException, ParseException {
        br.mark(100);
        String[] line = this.readLine();
        if (line == null) {
            return -1;
        }
        this.br.reset();
//        return new SimpleDateFormat(Constants.flightDateFormat).parse(line[0]).getTime();
        return new SimpleDateFormat(Constants.flightDateFormat2).parse(line[6]).getTime();
    }

    public Flight getFlight() throws IOException, ParseException {
        String[] line = this.readLine();
        if (line == null) {
            return null;
        }
//        return new Flight(
//                new SimpleDateFormat(Constants.flightDateFormat).parse(line[0]).getTime(),
//                line[1],
//                Duration.ofSeconds(Integer.parseInt(line[3])),
//                Duration.ofSeconds(Integer.parseInt(line[5])),
//                Duration.ofSeconds(Integer.parseInt(line[6]))
//        );
        return new Flight(
                new SimpleDateFormat(Constants.flightDateFormat2).parse(line[6]).getTime(),
                line[1],
                Duration.ofSeconds(Integer.parseInt(line[3])),
                Duration.ofSeconds(Integer.parseInt(line[5])),
                Duration.ofSeconds(Integer.parseInt(line[7]))
        );
    }
}
