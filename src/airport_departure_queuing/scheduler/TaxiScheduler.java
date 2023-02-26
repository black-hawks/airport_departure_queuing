package airport_departure_queuing.scheduler;

import airport_departure_queuing.doublyPriorityQueue.PriorityQueue;
import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.flight.FlightReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;

public class TaxiScheduler implements Scheduler {
    static Logger logger = LoggerFactory.getLogger(TaxiScheduler.class);
    private final FlightReader reader;
    private final PriorityQueue taxi;

    public TaxiScheduler(PriorityQueue taxi, FlightReader reader) {
        this.reader = reader;
        this.taxi = taxi;
    }

    public void schedule(long currentTimestamp) throws IOException, ParseException {
        long pushbackTimestamp = reader.getDate();
        // While loop to fetch all flights at currentTimestamp
        if (pushbackTimestamp <= currentTimestamp) {
            Flight flight = reader.getFlight();
            long wheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(flight);
            flight.setExpectedWheelOffTimestamp(wheelOffTimestamp);
            flight.setActualWheelOffTimestamp(wheelOffTimestamp);
            logger.debug("Processing " + flight.toShortString());
            taxi.enqueue(flight);
            logger.debug("Taxi Priority Queue " + taxi);
        }
    }
}
