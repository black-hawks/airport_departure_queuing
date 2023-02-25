package airport_departure_queuing.flight;

import java.time.Duration;
import java.time.Instant;

public class FlightEstimator {
    public static long estimateWheelOffTimestamp(Flight flight) {
        Instant pushbackTimeInstant = Instant.ofEpochMilli(flight.getPushbackTimestamp());
        Instant estimateTime = pushbackTimeInstant
                .plus(flight.getUnimpededTime())
                .plus(flight.getTaxiOutTime())
                .plus(flight.getDelay());
        return estimateTime.toEpochMilli();
    }

    public static long estimateWheelOffTimestamp(Flight flight, double queuingDelay) {
        Duration alphaRT = Duration.ofSeconds((long) (queuingDelay * 60));
        Instant wheelOffTimestampInstant = Instant.ofEpochMilli(flight.getActualWheelOffTimestamp());
        Instant newWheelOffTimestamp = wheelOffTimestampInstant.plus(alphaRT);
        return newWheelOffTimestamp.toEpochMilli();
    }
}
