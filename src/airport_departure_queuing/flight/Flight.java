package airport_departure_queuing.flight;

import java.time.Duration;
import java.util.Date;

public class Flight {
    private final long pushbackTimestamp;
    private final String airline;
    private final Duration unimpededTime;
    private final Duration taxiOutTime;
    private final Duration delay;
    private long expectedWheelOffTimestamp;

    private long actualWheelOffTimestamp;

    public Flight(long pushbackTimestamp, String airline, Duration unimpededTime, Duration taxiOutTime, Duration delay) {
        this.pushbackTimestamp = pushbackTimestamp;
        this.airline = airline;
        this.unimpededTime = unimpededTime;
        this.taxiOutTime = taxiOutTime;
        this.delay = delay;
    }

    public String toString() {
        return "Flight{"
                + "pushbackTimestamp=" + new Date(pushbackTimestamp)
                + ", airline='" + airline + '\''
                + ", unimpededTime=" + unimpededTime
                + ", taxiOutTime=" + taxiOutTime
                + ", delay=" + delay
                + ", expectedWheelOffTimestamp=" + new Date(expectedWheelOffTimestamp)
                + ", actualWheelOffTimestamp=" + new Date(actualWheelOffTimestamp)
                + '}';
    }

    public long getPushbackTimestamp() {
        return pushbackTimestamp;
    }

    public String getAirline() {
        return airline;
    }

    public Duration getUnimpededTime() {
        return unimpededTime;
    }

    public Duration getTaxiOutTime() {
        return taxiOutTime;
    }

    public Duration getDelay() {
        return delay;
    }

    public long getExpectedWheelOffTimestamp() {
        return expectedWheelOffTimestamp;
    }

    public void setExpectedWheelOffTimestamp(long expectedWheelOffTimestamp) {
        this.expectedWheelOffTimestamp = expectedWheelOffTimestamp;
    }

    public long getActualWheelOffTimestamp() {
        return actualWheelOffTimestamp;
    }

    public void setActualWheelOffTimestamp(long actualWheelOffTimestamp) {
        this.actualWheelOffTimestamp = actualWheelOffTimestamp;
    }
}