package airport_departure_queuing;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Simulator {
    public Date currentTime;
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
}
