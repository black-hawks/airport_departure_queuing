package airport_departure_queuing.common;

import java.time.Instant;
import java.util.Date;

public class Utils {
    public static boolean isBefore(Date first, Date second) {
        Instant firstInstant = first.toInstant();
        Instant secondInstant = second.toInstant();
        return firstInstant.isBefore(secondInstant) || firstInstant.equals(secondInstant);
    }
}
