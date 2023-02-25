package airport_departure_queuing.flight;

import java.time.Duration;
import java.util.Date;

public class Flight {
    private final long pushbackTimestamp;
    private final String airline;
    private final Duration unimpededTime;
    private final Duration taxiOutTime;
    private final Duration delay;
    private long wheelOffTimestamp;

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
                + ", wheelOffTimestamp=" + new Date(wheelOffTimestamp)
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

    public long getWheelOffTimestamp() {
        return wheelOffTimestamp;
    }

    public void setWheelOffTimestamp(long wheelOffTimestamp) {
        this.wheelOffTimestamp = wheelOffTimestamp;
    }
}