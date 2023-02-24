package airport_departure_queuing;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.flight.FlightReader;
import airport_departure_queuing.common.Constants;
import airport_departure_queuing.common.Simulator;
import airport_departure_queuing.common.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Duration timeIncrement = Duration.ofMinutes(1);
        try {
            FlightReader reader = new FlightReader(Constants.dataFilePath);
            Date startDate = reader.getDate();
            Simulator simulator = new Simulator(startDate, timeIncrement);
            while (true) {
                Date flightDate = reader.getDate();
                if (flightDate == null) {
                    break;
                }
                Date currentDate = simulator.getCurrentTime();
                if (Utils.isBefore(flightDate, currentDate)) {
                    Flight flight = reader.getFlight();
                    Date wheelOffTime = FlightEstimator.estimateTakeOffTime(flight);
                    flight.setWheelOffTime(wheelOffTime);
                    // enqueue to first queue
                }
                simulator.simulateTime();
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
