package airport_departure_queuing.flight;

import airport_departure_queuing.common.CSVReader;
import airport_departure_queuing.common.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class FlightReader extends CSVReader {
    public FlightReader(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    public FlightReader(String filepath, String delimiter) throws FileNotFoundException {
        super(filepath, delimiter);
    }

    public Date getDate() throws IOException, ParseException {
        br.mark(100);
        String[] line = this.readLine();
        if (line == null) {
            return null;
        }
        this.br.reset();
        return new SimpleDateFormat(Constants.flightDateFormat).parse(line[0]);
    }

    public airport_departure_queuing.flight.Flight getFlight() throws IOException, ParseException {
        String[] line = this.readLine();
        if (line == null) {
            return null;
        }
        return new airport_departure_queuing.flight.Flight(
                new SimpleDateFormat(Constants.flightDateFormat).parse(line[0]),
                line[1],
                Duration.ofSeconds(Integer.parseInt(line[3])),
                Duration.ofSeconds(Integer.parseInt(line[5])),
                Duration.ofSeconds(Integer.parseInt(line[6]))
        );
    }
}
