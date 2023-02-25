package airport_departure_queuing.flight;

import airport_departure_queuing.common.Metrics;

import java.time.Duration;
import java.util.Date;

public class Flight {
    private final Date pushbackTime;
    private final String airline;
    private final Duration unimpededTime;
    private final Duration taxiOutTime;
    private final Duration delay;
    private Date wheelOffTime;

    public Flight(Date pushbackTime, String airline, Duration unimpededTime, Duration taxiOutTime, Duration delay) {
        this.pushbackTime = pushbackTime;
        this.airline = airline;
        this.unimpededTime = unimpededTime;
        this.taxiOutTime = taxiOutTime;
        this.delay = delay;
    }

    public Date getPushbackTime() {
        return pushbackTime;
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

    public void setWheelOffTime(Date wheelOffTime) {
        this.wheelOffTime = wheelOffTime;
        Metrics.estimationTotal.inc();
    }

    public Date getWheelOffTime() {
        return wheelOffTime;
    }
}