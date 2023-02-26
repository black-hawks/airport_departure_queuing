/**

 A utility class for estimating the wheel-off timestamp of a flight.
 */
package airport_departure_queuing.flight;

import java.time.Duration;
import java.time.Instant;

public class FlightEstimator {
    /**
     * Estimates the wheel-off timestamp of a flight based on its pushback time, unimpeded time, taxi-out time, and delay.
     *
     * @param flight the Flight object for which to estimate the wheel-off timestamp
     * @return the estimated wheel-off timestamp in milliseconds since the Unix epoch
     */
    public static long estimateWheelOffTimestamp(Flight flight) {
        Instant pushbackTimeInstant = Instant.ofEpochMilli(flight.getPushbackTimestamp());
        Instant estimateTime = pushbackTimeInstant
                .plus(flight.getUnimpededTime())
                .plus(flight.getTaxiOutTime())
                .plus(flight.getDelay());
        return estimateTime.toEpochMilli();
    }

    /**
     * Estimates the wheel-off timestamp of a flight based on its actual wheel-off time and a queuing delay.
     *
     * @param flight the Flight object for which to estimate the wheel-off timestamp
     * @param queuingDelay the queuing delay, in minutes
     * @return the estimated wheel-off timestamp in milliseconds since the Unix epoch
     */
    public static long estimateWheelOffTimestamp(Flight flight, double queuingDelay) {
        Duration alphaRT = Duration.ofSeconds((long) (queuingDelay * 60));
        Instant wheelOffTimestampInstant = Instant.ofEpochMilli(flight.getActualWheelOffTimestamp());
        Instant newWheelOffTimestamp = wheelOffTimestampInstant.plus(alphaRT);
        return newWheelOffTimestamp.toEpochMilli();
    }
}
