/**

 The {@code WheelOffScheduler} class implements the {@code Scheduler} interface to schedule the departure of flights
 whose actual wheel off timestamp is equal to the current timestamp.
 It removes the flight from the departure queue and records flight metrics using a {@code Metrics} object.
 */
package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.fixedsizeQueue.FixedSizeQueue;
import airport_departure_queuing.util.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WheelOffScheduler implements Scheduler {

    static Logger logger = LoggerFactory.getLogger(WheelOffScheduler.class);
    FixedSizeQueue departure;
    Metrics metrics;

    /**
     * Constructs a {@code WheelOffScheduler} object with the specified departure queue.
     *
     * @param departure the queue of flights waiting to depart.
     */
    public WheelOffScheduler(FixedSizeQueue departure) {
        this.departure = departure;
        metrics = new Metrics("flightMetrics.csv");
    }

    /**
     * Schedules the departure of flights whose actual wheel off timestamp is equal to the current timestamp.
     * It removes the flight from the departure queue and records flight metrics using a {@code Metrics} object.
     *
     * @param currentTimestamp the current timestamp.
     */
    public void schedule(long currentTimestamp) {
        Flight departingFlight = departure.peek();
        // ACTUAL WHEEL OFF TIMESTAMP IS CURRENT TIMESTAMP
        if (departingFlight != null && departingFlight.getActualWheelOffTimestamp() <= currentTimestamp) {
//      departingFlight.setActualWheelOffTimestamp(currentTimestamp);
            departure.removeFlight();
//            departingFlight.setActualWheelOffTimestamp(currentTimestamp);
            metrics.recordFlight(departingFlight);
            logger.debug("Wheel off: " + departingFlight.toShortString());
        }
    }
}
