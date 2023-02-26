/**

 Flight class represents a single flight with its attributes.
 */
package airport_departure_queuing.flight;

import airport_departure_queuing.util.Constants;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Flight {

    private final long pushbackTimestamp;
    private final String airline;
    private final Duration unimpededTime;
    private final Duration taxiOutTime;
    private final Duration delay;
    private long expectedWheelOffTimestamp;

    private long actualWheelOffTimestamp;

    /**

     Constructor to create a Flight object with the specified parameters.
     @param pushbackTimestamp Timestamp when the flight was pushed back from the gate.
     @param airline Airline of the flight.
     @param unimpededTime Duration of the unimpeded time.
     @param taxiOutTime Duration of the taxi-out time.
     @param delay Duration of the delay.
     */
    public Flight(long pushbackTimestamp, String airline, Duration unimpededTime, Duration taxiOutTime, Duration delay) {
        this.pushbackTimestamp = pushbackTimestamp;
        this.airline = airline;
        this.unimpededTime = unimpededTime;
        this.taxiOutTime = taxiOutTime;
        this.delay = delay;
    }

    /**

     Returns a string representation of the Flight object.
     @return A string representation of the Flight object.
     */
    public String toString() {
        return "Flight{"
                + "pushbackTimestamp=" + new Date(pushbackTimestamp)
                + ", airline='" + airline + '\''
                + ", unimpededTime=" + unimpededTime
                + ", taxiOutTime=" + taxiOutTime
                + ", delay=" + delay
                + ", expectedWheelOffTimestamp=" + new Date(expectedWheelOffTimestamp)
                + ", actualWheelOffTimestamp=" + new Date(actualWheelOffTimestamp)
                + '}';
    }

    /**

     Returns a short string representation of the Flight object.
     @return A short string representation of the Flight object.
     */
    public String toShortString() {
        return "Flight{"
                + '\'' + airline + '\''
                + ", pushback=" + new SimpleDateFormat(Constants.flightDateFormat2).format(new Date(pushbackTimestamp))
                + ", expected=" + new SimpleDateFormat(Constants.flightDateFormat2).format(new Date(expectedWheelOffTimestamp))
                + ", actual=" + new SimpleDateFormat(Constants.flightDateFormat2).format(new Date(actualWheelOffTimestamp));
    }

    /**

     Returns the pushback timestamp.
     @return The pushback timestamp.
     */
    public long getPushbackTimestamp() {
        return pushbackTimestamp;
    }

    /**

     Returns the airline of the flight.
     @return The airline of the flight.
     */
    public String getAirline() {
        return airline;
    }


    /**

     Returns the unimpeded time of the Flight.
     @return the unimpeded time of the Flight
     */
    public Duration getUnimpededTime() {
        return unimpededTime;
    }

    /**

     Returns the taxi-out time of the Flight.
     @return the taxi-out time of the Flight
     */
    public Duration getTaxiOutTime() {
        return taxiOutTime;
    }

    /**

     Returns the delay of the Flight.
     @return the delay of the Flight
     */
    public Duration getDelay() {
        return delay;
    }

    /**

     Returns the expected wheel-off time of the Flight.
     @return the expected wheel-off time of the Flight
     */
    public long getExpectedWheelOffTimestamp() {
        return expectedWheelOffTimestamp;
    }

    /**

     Sets the expected wheel-off timestamp for this flight.
     @param expectedWheelOffTimestamp the expected wheel-off timestamp in milliseconds
     */
    public void setExpectedWheelOffTimestamp(long expectedWheelOffTimestamp) {
        this.expectedWheelOffTimestamp = expectedWheelOffTimestamp;
    }

    /**

     Returns the actual wheel-off timestamp for this flight.
     @return the actual wheel-off timestamp in milliseconds
     */
    public long getActualWheelOffTimestamp() {
        return actualWheelOffTimestamp;
    }

    /**

     Sets the actual wheel-off timestamp for this flight.
     @param actualWheelOffTimestamp the actual wheel-off timestamp in milliseconds
     */
    public void setActualWheelOffTimestamp(long actualWheelOffTimestamp) {
        this.actualWheelOffTimestamp = actualWheelOffTimestamp;
    }
}