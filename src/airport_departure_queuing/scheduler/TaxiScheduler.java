package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.flight.FlightReader;
import airport_departure_queuing.queue.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;

public class TaxiScheduler extends Scheduler {
    static Logger logger = LoggerFactory.getLogger(TaxiScheduler.class);
    private final FlightReader reader;

    public TaxiScheduler(PriorityQueue taxi, FlightReader reader) {
        super(taxi);
        this.reader = reader;
    }

    @Override
    public void schedule(long currentTimestamp) throws IOException, ParseException {
        long pushbackTimestamp = reader.getDate();
        // While loop to fetch all flights at currentTimestamp
        if (pushbackTimestamp <= currentTimestamp) {
            Flight flight = reader.getFlight();
            long wheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(flight);
            flight.setExpectedWheelOffTimestamp(wheelOffTimestamp);
            flight.setActualWheelOffTimestamp(wheelOffTimestamp);
            logger.debug("Processing " + flight);
            taxi.addFlight(flight);
            logger.debug(taxi.toString());
        }
    }
}
