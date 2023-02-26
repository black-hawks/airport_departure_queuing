package airport_departure_queuing.util;

import airport_departure_queuing.flight.Flight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Metrics {
    private final String filename;

    public Metrics(String filename) {
        this.filename = filename;
    }

    public void recordFlight(Flight flight) {
        Date pushbackTime = new Date(flight.getPushbackTimestamp());
        Date expectedWheelOffTime = new Date(flight.getExpectedWheelOffTimestamp());
        Date actualWheelOffTime = new Date(flight.getActualWheelOffTimestamp());
        long delayInSeconds = (flight.getActualWheelOffTimestamp() - flight.getExpectedWheelOffTimestamp()) / 1000;
        String line = String.join(",",
                pushbackTime.toString(),
                expectedWheelOffTime.toString(),
                actualWheelOffTime.toString(),
                String.valueOf(delayInSeconds)
        );
        writeToCsv(line);
    }

    private void writeToCsv(String line) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.filename, true));
            writer.println(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
