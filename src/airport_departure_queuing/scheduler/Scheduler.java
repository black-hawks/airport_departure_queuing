package airport_departure_queuing.scheduler;

import java.io.IOException;
import java.text.ParseException;

public interface Scheduler {
    void schedule(long currentTimestamp) throws IOException, ParseException;
}
