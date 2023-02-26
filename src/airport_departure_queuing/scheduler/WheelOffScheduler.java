package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.FixedSizeQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WheelOffScheduler implements Scheduler {

    static Logger logger = LoggerFactory.getLogger(WheelOffScheduler.class);
    FixedSizeQueue departure;

    public WheelOffScheduler(FixedSizeQueue departure) {
        this.departure = departure;
    }

    public void schedule(long currentTimestamp) {
        Flight departingFlight = departure.peek();
        // ACTUAL WHEEL OFF TIMESTAMP IS CURRENT TIMESTAMP
        if (departingFlight != null && departingFlight.getActualWheelOffTimestamp() <= currentTimestamp) {
//      departingFlight.setActualWheelOffTimestamp(currentTimestamp);
            departure.removeFlight();
            logger.debug("Wheel off: " + departingFlight.toShortString());
        }
    }
}
