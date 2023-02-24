package airport_departure_queuing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Flight {
    private String flightNumber;
    private Date pushbackTime;
    private Duration unimpededTime;

    public Flight(String flightNumber, Date pushbackTime, Duration unimpededTime) {
        this.flightNumber = flightNumber;
        this.pushbackTime = pushbackTime;
        this.unimpededTime = unimpededTime;
    }

    static Flight fromCsv(String[] line) throws ParseException {
        new SimpleDateFormat("dd/MM/yyyy").parse(line[1]);
        return new Flight(
                line[0],
                new SimpleDateFormat("dd/MM/yyyy").parse(line[1]),
                Duration.ofMinutes(Integer.parseInt(line[2]))
        );
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getPushbackTime() {
        return pushbackTime;
    }

    public Duration getUnimpededTime() {
        return unimpededTime;
    }
}