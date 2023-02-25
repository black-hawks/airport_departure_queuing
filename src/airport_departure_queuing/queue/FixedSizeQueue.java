package airport_departure_queuing.queue;

import airport_departure_queuing.flight.Flight;

import java.time.Duration;
import java.time.Instant;

public class FixedSizeQueue extends Queue {
    int size;
    int currentQueueLength;

    public FixedSizeQueue(int size) {
        super();
        this.size = size;
        this.currentQueueLength = 0;
    }

    // if size of queue is exceeded, then say sorry queue full
    // if the one being inserted and the current first node has same time, then add 1 minute to the node being inserted
    // TODO: return type as boolean throws error
    public void insertAtEnd(Flight flight) {
        if (currentQueueLength >= size) {
//            return false;
            return;
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
        }
        long newFlightEstimate = flight.getWheelOffTimestamp();
        long previousFlightEstimate = currNode.flight.getWheelOffTimestamp();
        if (newFlightEstimate == previousFlightEstimate) {
            Instant wheelOffInstant = Instant.ofEpochMilli(flight.getWheelOffTimestamp());
            Duration delay = Duration.ofMinutes(1);
            long newWheelOffTimestamp = wheelOffInstant.plus(delay).toEpochMilli();
            newNode.flight.setWheelOffTimestamp(newWheelOffTimestamp);
        }
        currentQueueLength++;
//        return true;
    }
}
