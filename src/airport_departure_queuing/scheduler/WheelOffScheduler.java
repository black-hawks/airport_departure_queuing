package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.FixedSizeQueue;
import airport_departure_queuing.util.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WheelOffScheduler implements Scheduler {

    static Logger logger = LoggerFactory.getLogger(WheelOffScheduler.class);
    FixedSizeQueue departure;
    Metrics metrics;

    public WheelOffScheduler(FixedSizeQueue departure) {
        this.departure = departure;
        metrics = new Metrics("flightMetrics.csv");
    }

    public void schedule(long currentTimestamp) {
        Flight departingFlight = departure.peek();
        if (departingFlight != null && departingFlight.getActualWheelOffTimestamp() <= currentTimestamp) {
            departure.removeFlight();
            metrics.recordFlight(departingFlight);
            logger.debug("Wheel off: " + departingFlight.toShortString());
        }
    }
}
