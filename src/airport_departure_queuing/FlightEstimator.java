package airport_departure_queuing;

import java.time.Instant;
import java.util.Date;

public class FlightEstimator {
    public Date getEstimateTakeOffTime(Flight flight) {
        Instant pushbackTimeInstant = flight.getPushbackTime().toInstant();
        Instant estimateTime = pushbackTimeInstant.plus(flight.getUnimpededTime());
        return Date.from(estimateTime);
    }
}
