package airport_departure_queuing.common;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Simulator {
    private Date currentTime;
    private Duration timeIncrement;

    public Simulator(Date currentTime, Duration timeIncrement) {
        this.currentTime = currentTime;
        this.timeIncrement = timeIncrement;
    }

    public void simulateTime() {
        Instant currentTimeInstant = currentTime.toInstant();
        Instant newTimeInstant = currentTimeInstant.plus(timeIncrement);
        currentTime = Date.from(newTimeInstant);
    }

    public Date getCurrentTime() {
        return currentTime;
    }
}
