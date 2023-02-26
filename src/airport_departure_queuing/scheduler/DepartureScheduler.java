package airport_departure_queuing.scheduler;

import airport_departure_queuing.doublyPriorityQueue.PriorityQueue;
import airport_departure_queuing.flight.Flight;
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
//        there might be a possibility where at the same currentTimestamp, there is more than one flight,
//        you need to try to enqueue the flight one by one in the same loop.
        Flight flight = taxi.peek();
        while (flight != null && flight.getActualWheelOffTimestamp() <= currentTimestamp) {
            if (departure.addFlight(flight)) {
                taxi.dequeue();
            } else {
                break;
            }
            logger.debug(departure.toString());
        }
    }
}
