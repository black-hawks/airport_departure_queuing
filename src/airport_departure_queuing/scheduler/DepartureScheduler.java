package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.FixedSizeQueue;
import airport_departure_queuing.queue.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartureScheduler extends Scheduler {
    static Logger logger = LoggerFactory.getLogger(DepartureScheduler.class);
    FixedSizeQueue departure;

    public DepartureScheduler(PriorityQueue taxi) {
        super(taxi);
    }

    public DepartureScheduler(PriorityQueue taxi, FixedSizeQueue departure) {
        super(taxi);
        this.departure = departure;
    }

    @Override
    public void schedule(long currentTimestamp) {
        if(taxi.peek().getActualWheelOffTimestamp() <= currentTimestamp) {

            if(departure.addFlight(taxi.peek())) {
                taxi.deleteAtStart();
            }
            logger.debug(taxi.toString());
        }
    }
}
