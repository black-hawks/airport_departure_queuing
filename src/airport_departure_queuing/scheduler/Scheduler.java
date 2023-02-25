package airport_departure_queuing.scheduler;

import airport_departure_queuing.queue.PriorityQueue;

import java.io.IOException;
import java.text.ParseException;

public abstract class Scheduler {
    PriorityQueue taxi;

    public Scheduler(PriorityQueue taxi) {
        this.taxi = taxi;
    }

    public Scheduler() { }
    public abstract void schedule(long currentTimestamp) throws IOException, ParseException;
}
