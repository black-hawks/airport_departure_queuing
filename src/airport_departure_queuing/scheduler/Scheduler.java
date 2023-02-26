/**

 The Scheduler interface defines the behavior of a departure scheduler.
 Implementations of this interface are responsible for scheduling flights from a taxi queue to a departure queue.
 The scheduling of flights is based on the current timestamp and the estimated time for each flight to start the takeoff roll.
 */

package airport_departure_queuing.scheduler;

import java.io.IOException;
import java.text.ParseException;

public interface Scheduler {
    /**
     * Schedules flights from the taxi queue to the departure queue based on the current timestamp.
     *
     * @param currentTimestamp the current timestamp to use as the scheduling reference
     * @throws IOException if an I/O error occurs while scheduling
     * @throws ParseException if an error occurs while parsing the flight data during scheduling
     */
    void schedule(long currentTimestamp) throws IOException, ParseException;
}
