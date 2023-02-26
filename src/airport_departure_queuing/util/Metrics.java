package airport_departure_queuing.util;

import airport_departure_queuing.flight.Flight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Metrics {
    private final String filename;

    public Metrics(String filename) {
        this.filename = filename;
    }

    public void recordFlight(Flight flight) {
        String pushbackTime = new SimpleDateFormat(Constants.flightExcelDateFormat).format(new Date(flight.getPushbackTimestamp()));
        Date expectedWheelOffTime = new Date(flight.getExpectedWheelOffTimestamp());
        Date actualWheelOffTime = new Date(flight.getActualWheelOffTimestamp());
        long delayInSeconds = (flight.getActualWheelOffTimestamp() - flight.getExpectedWheelOffTimestamp()) / 1000;
        long totalTravelTime = (flight.getActualWheelOffTimestamp() - flight.getPushbackTimestamp()) / 1000;
        String line = String.join(",",
                pushbackTime,
                expectedWheelOffTime.toString(),
                actualWheelOffTime.toString(),
                String.valueOf(totalTravelTime),
                String.valueOf(delayInSeconds)
        );
        writeToCsv(line);
    }

    public void recordQueueState(long time, int queueSize, long duration) {
        String currentTime = new SimpleDateFormat(Constants.flightExcelDateFormat).format(new Date(time));
        String line = String.join(",",
                currentTime,
                String.valueOf(queueSize),
                String.valueOf(duration)
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
