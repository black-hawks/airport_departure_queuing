package airport_departure_queuing.scheduler;

import airport_departure_queuing.queue.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartureScheduler extends Scheduler {
    static Logger logger = LoggerFactory.getLogger(TaxiScheduler.class);

    public DepartureScheduler(PriorityQueue taxi) {
        super(taxi);
    }

    @Override
    public void schedule(long currentTimestamp) {
        while (taxi.peek().getActualWheelOffTimestamp() <= currentTimestamp) {
            taxi.deleteAtStart();
            logger.debug(taxi.toString());
        }
    }
}
