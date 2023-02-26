package airport_departure_queuing.scheduler;

import airport_departure_queuing.queue.FixedSizeQueue;
import airport_departure_queuing.queue.PriorityQueue;
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
//        there might be a possibility where at the same currentTimestamp, there is more than one flight,
//        you need to try to enqueue the flight one by one in the same loop.
        while (taxi.peek() != null && taxi.peek().getActualWheelOffTimestamp() <= currentTimestamp) {
            if (departure.addFlight(taxi.peek())) {
                taxi.deleteAtStart();
            } else {
                break;
            }
            logger.debug(departure.toString());
        }
    }
}
