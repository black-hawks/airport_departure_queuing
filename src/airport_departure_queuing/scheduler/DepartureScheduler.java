package airport_departure_queuing.scheduler;

import airport_departure_queuing.doublyPriorityQueue.PriorityQueue;
import airport_departure_queuing.queue.FixedSizeQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartureScheduler implements Scheduler {
    static Logger logger = LoggerFactory.getLogger(DepartureScheduler.class);
    private final FixedSizeQueue departure;
    private final PriorityQueue taxi;

    public DepartureScheduler(PriorityQueue taxi, FixedSizeQueue departure) {
        this.taxi = taxi;
        this.departure = departure;
    }

    public void schedule(long currentTimestamp) {
        while (taxi.peek() != null && taxi.peek().getActualWheelOffTimestamp() <= currentTimestamp) {
            if (departure.addFlight(taxi.peek())) {
                taxi.dequeue();
            } else {
                break;
            }
            logger.debug("Departure Fixed Queue " + departure);
        }
    }
}
