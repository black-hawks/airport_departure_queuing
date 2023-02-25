package airport_departure_queuing.scheduler;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.queue.FixedSizeQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WheelOffScheduler extends Scheduler {

  static Logger logger = LoggerFactory.getLogger(WheelOffScheduler.class);
  FixedSizeQueue departure;

  public WheelOffScheduler() { super(); }

  public WheelOffScheduler(FixedSizeQueue departure) {
    this.departure = departure;
  }

  @Override
  public void schedule(long currentTimestamp) {
    Flight departingFlight = departure.peek();
    // ACTUAL WHEEL OFF TIMESTAMP IS CURRENT TIMESTAMP
    if(departingFlight != null) {
      departingFlight.setActualWheelOffTimestamp(currentTimestamp);
      departure.deleteAtStart();
      logger.debug(departingFlight.toString());
    }

    logger.debug("Departure queue is empty");
  }
}
