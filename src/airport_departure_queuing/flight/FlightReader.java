/**

 FlightReader class reads flight information from a CSV file and returns flight data in Flight objects.

 The flight data includes flight departure time, flight number, and flight durations.
 */
package airport_departure_queuing.flight;

import airport_departure_queuing.util.CSVReader;
import airport_departure_queuing.util.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class FlightReader extends CSVReader {
    /**

     Constructor for FlightReader class.
     @param filepath the file path of the CSV file to be read
     @throws FileNotFoundException if the CSV file is not found
     */
    public FlightReader(String filepath) throws FileNotFoundException {
        super(filepath);
    }
    /**

     Constructor for FlightReader class.
     @param filepath the file path of the CSV file to be read
     @param delimiter the delimiter used in the CSV file
     @throws FileNotFoundException if the CSV file is not found
     */
    public FlightReader(String filepath, String delimiter) throws FileNotFoundException {
        super(filepath, delimiter);
    }

    /**

     Gets the departure date of the flight from the CSV file.
     @return the departure date of the flight in milliseconds since the epoch
     @throws IOException if an I/O error occurs
     @throws ParseException if the date format is not valid
     */
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

    /**

     Gets the flight data from the CSV file and creates a Flight object.
     @return a Flight object containing the flight data
     @throws IOException if an I/O error occurs
     @throws ParseException if the date or duration format is not valid
     */
    public Flight getFlight() throws IOException, ParseException {
        String[] line = this.readLine();
        if (line == null) {
            return null;
        }

        return new Flight(
                new SimpleDateFormat(Constants.flightDateFormat2).parse(line[6]).getTime(),
                line[1],
                Duration.ofSeconds(Integer.parseInt(line[3])),
                Duration.ofSeconds(Integer.parseInt(line[5])),
                Duration.ofSeconds(Integer.parseInt(line[7]))
        );
    }
}
