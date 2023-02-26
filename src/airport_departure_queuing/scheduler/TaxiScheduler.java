/**

 The TaxiScheduler class is responsible for scheduling the taxis at the airport.
 It reads the flight information from the FlightReader and enqueues them in the PriorityQueue
 according to their expected wheel-off time.
 */
package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.flight.FlightReader;
import airport_departure_queuing.priorityQueue.PriorityQueue;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

public class TaxiScheduler implements Scheduler {
    static Logger logger = Logger.getLogger(TaxiScheduler.class.getName());
    private final FlightReader reader;
    private final PriorityQueue taxi;

    /**
     * Constructor for the TaxiScheduler class.
     *
     * @param taxi   The PriorityQueue containing the taxis waiting for departure.
     * @param reader The FlightReader used to read flight information.
     */
    public TaxiScheduler(PriorityQueue taxi, FlightReader reader) {
        this.reader = reader;
        this.taxi = taxi;
    }

    /**
     * Reads flight information from the FlightReader and enqueues them in the PriorityQueue
     * according to their expected wheel-off time.
     *
     * @param currentTimestamp The current time.
     * @throws IOException    If an I/O error occurs.
     * @throws ParseException If the flight information is not in the expected format.
     */
    public void schedule(long currentTimestamp) throws IOException, ParseException {
        long pushbackTimestamp = reader.getDate();
        // While loop to fetch all flights at currentTimestamp
        if (pushbackTimestamp <= currentTimestamp) {
            Flight flight = reader.getFlight();
            long wheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(flight);
            flight.setExpectedWheelOffTimestamp(wheelOffTimestamp);
            flight.setActualWheelOffTimestamp(wheelOffTimestamp);
            logger.info("Processing " + flight.toShortString());
            taxi.enqueue(flight);
            logger.info(taxi.toString());
        }
    }
}
