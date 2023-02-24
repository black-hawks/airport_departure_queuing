package airport_departure_queuing.flight;

import java.time.Duration;
import java.util.Date;

public class Flight {
    private Date pushbackTime;
    private String airline;
    private Duration unimpededTime;
    private Duration taxiOutTime;
    private Duration delay;
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
    }

    public Date getWheelOffTime() {
        return wheelOffTime;
    }
}