package airport_departure_queuing.flight;

import java.time.Instant;
import java.util.Date;

public class FlightEstimator {
    public static Date estimateTakeOffTime(airport_departure_queuing.flight.Flight flight) {
        Instant pushbackTimeInstant = flight.getPushbackTime().toInstant();
        Instant estimateTime = pushbackTimeInstant.plus(flight.getUnimpededTime());
        return Date.from(estimateTime);
    }
}
