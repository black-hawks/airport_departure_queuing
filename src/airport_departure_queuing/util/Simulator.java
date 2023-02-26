package airport_departure_queuing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class Simulator {
    static Logger logger = LoggerFactory.getLogger(Simulator.class);
    private final Duration timeIncrement;
    private long currentTimestamp;

    public Simulator(long currentTimestamp, Duration timeIncrement) {
        this.currentTimestamp = currentTimestamp;
        this.timeIncrement = timeIncrement;
    }

    public void simulateTime() {
        Instant currentTimeInstant = Instant.ofEpochMilli(currentTimestamp);
        Instant newTimeInstant = currentTimeInstant.plus(timeIncrement);
        currentTimestamp = newTimeInstant.toEpochMilli();
    }

    public long getCurrentTimestamp() {
        return currentTimestamp;
    }
}
