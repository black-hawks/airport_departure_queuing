package airport_departure_queuing;

import airport_departure_queuing.flight.FlightReader;
import airport_departure_queuing.queue.FixedSizeQueue;
import airport_departure_queuing.queue.PriorityQueue;
import airport_departure_queuing.scheduler.DepartureScheduler;
import airport_departure_queuing.scheduler.TaxiScheduler;
import airport_departure_queuing.scheduler.WheelOffScheduler;
import airport_departure_queuing.util.Constants;
import airport_departure_queuing.util.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);
    static PriorityQueue taxi = new PriorityQueue();
    static FixedSizeQueue departure = new FixedSizeQueue(5);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Duration timeIncrement = Duration.ofMinutes(1);
        try {
            FlightReader reader = new FlightReader(Constants.dataFilePath);
            Simulator simulator = new Simulator(reader.getDate(), timeIncrement);

            TaxiScheduler taxiScheduler = new TaxiScheduler(taxi, reader);
            DepartureScheduler departureScheduler = new DepartureScheduler(taxi, departure);
            WheelOffScheduler wheelOffScheduler = new WheelOffScheduler(departure);
            while (true) {
                long pushbackTimestamp = reader.getDate();
                if (pushbackTimestamp == -1) {
                    break;
                }
                long currentTimestamp = simulator.getCurrentTimestamp();
                taxiScheduler.schedule(currentTimestamp);
                departureScheduler.schedule(currentTimestamp);
                wheelOffScheduler.schedule(currentTimestamp);
                simulator.simulateTime();
            }
            while (taxi.peek() != null || departure.peek() != null) {
                long currentTimestamp = simulator.getCurrentTimestamp();
                departureScheduler.schedule(currentTimestamp);
                wheelOffScheduler.schedule(currentTimestamp);
                simulator.simulateTime();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        logger.info("Time took: " + (System.currentTimeMillis() - start));
    }
}

// Departure queue and its scheduling
// Metrics collection and graphs, store expected and actual estimations, time for entire simulation