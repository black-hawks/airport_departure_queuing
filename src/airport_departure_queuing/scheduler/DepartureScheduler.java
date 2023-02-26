/**

 The DepartureScheduler class implements the {@link Scheduler} interface and is responsible for scheduling flights
 for departure from the airport.
 <p>
 The scheduler schedules flights from the {@link PriorityQueue} to the {@link FixedSizeQueue} based on the current
 timestamp and the estimated time required for the flights to taxi from their gate to the runway.
 </p>

 */
package airport_departure_queuing.scheduler;

import airport_departure_queuing.queue.priorityQueue.PriorityQueue;
import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.fixedsizeQueue.FixedSizeQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartureScheduler implements Scheduler {
    static Logger logger = LoggerFactory.getLogger(DepartureScheduler.class);
    private final FixedSizeQueue departure;
    private final PriorityQueue taxi;

    /**
     * Constructs a DepartureScheduler object with the specified taxi queue and departure queue.
     *
     * @param taxi      the {@link PriorityQueue} representing the queue of flights waiting to taxi to the runway
     * @param departure the {@link FixedSizeQueue} representing the queue of flights scheduled for departure
     */
    public DepartureScheduler(PriorityQueue taxi, FixedSizeQueue departure) {
        this.taxi = taxi;
        this.departure = departure;
    }

    /**
     * Schedules flights for departure from the airport based on the current timestamp and the estimated time
     * required for the flights to taxi from their gate to the runway.
     *
     * @param currentTimestamp the current timestamp at which the scheduler is run
     */
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
