/**

 Represents a fixed-size queue for managing flights departing from an airport.
 Flights can be added to the queue as long as the queue size has not been exceeded.
 If the queue is full, attempting to add a flight will return false.
 If a new flight has the same actual wheel-off time as the current first flight in the queue,
 its actual wheel-off time will be delayed by 1 minute.
 */
package airport_departure_queuing.fixedsizeQueue;

import airport_departure_queuing.flight.Flight;

import java.time.Duration;
import java.time.Instant;

public class FixedSizeQueue extends Queue {
    int size;
    int currentQueueLength;

    /**
     * Constructs a FixedSizeQueue with the specified size.
     * @param size The maximum number of flights that can be in the queue at any given time.
     */
    public FixedSizeQueue(int size) {
        super();
        this.size = size;
        this.currentQueueLength = 0;
    }


    // if size of queue is exceeded, then say sorry queue full
    // if the one being inserted and the current first node has same time, then add 1 minute to the node being inserted
    // TODO: return type as boolean throws error
    /**
     * Adds a flight to the queue.
     * If the queue is full, returns false.
     * If the new flight has the same actual wheel-off time as the current first flight in the queue,
     * its actual wheel-off time will be delayed by 1 minute.
     * @param flight The flight to be added to the queue.
     * @return true if the flight was successfully added to the queue, false otherwise.
     */
    public boolean addFlight(Flight flight) {
        if (currentQueueLength >= size) {
            return false;
        }

        Node currNode = headNode;
        Node newNode = new Node(flight);
        if (headNode == null) {
            insertAtStart(flight);
        } else {
            while (currNode.nextNode != null) {
                currNode = currNode.nextNode;
            }
            currNode.nextNode = newNode;
            newNode.nextNode = null;
            long newFlightEstimate = flight.getActualWheelOffTimestamp();

            long previousFlightEstimate = currNode.flight.getActualWheelOffTimestamp();
            if (newFlightEstimate == previousFlightEstimate) {
                Instant wheelOffInstant = Instant.ofEpochMilli(flight.getActualWheelOffTimestamp());
                Duration delay = Duration.ofMinutes(1);
                long newWheelOffTimestamp = wheelOffInstant.plus(delay).toEpochMilli();
                newNode.flight.setActualWheelOffTimestamp(newWheelOffTimestamp);
            }
        }
        currentQueueLength++;
        return true;
    }

    /**
     * Removes the first flight from the queue.
     */
    public void removeFlight() {
        deleteAtStart();
        currentQueueLength--;
    }
}
