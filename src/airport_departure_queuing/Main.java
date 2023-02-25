package airport_departure_queuing;

import airport_departure_queuing.common.Constants;
import airport_departure_queuing.common.Simulator;
import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.flight.FlightReader;
import airport_departure_queuing.queue.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);
    static PriorityQueue taxi = new PriorityQueue();

    public static void main(String[] args) {
        Duration timeIncrement = Duration.ofMinutes(1);
        try {
            FlightReader reader = new FlightReader(Constants.dataFilePath);
            long startTimestamp = reader.getDate();
            Simulator simulator = new Simulator(startTimestamp, timeIncrement);
            while (true) {
                long pushbackTimestamp = reader.getDate();
                if (pushbackTimestamp == -1) {
                    break;
                }
                long currentTimestamp = simulator.getCurrentTimestamp();
                if (pushbackTimestamp <= currentTimestamp) {
                    Flight flight = reader.getFlight();
                    long wheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(flight);
                    flight.setWheelOffTimestamp(wheelOffTimestamp);
                    logger.debug("Processing " + flight);
                    taxi.addFlight(flight);
                }
                simulator.simulateTime();
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
