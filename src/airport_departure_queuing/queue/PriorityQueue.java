package airport_departure_queuing.queue;

import airport_departure_queuing.common.Constants;
import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static airport_departure_queuing.flight.FlightEstimator.estimateWheelOffTimestamp;

public class PriorityQueue extends Queue {

    static Logger logger = LoggerFactory.getLogger(PriorityQueue.class);

    public PriorityQueue() {
        super();
    }

    public void addFlight(Flight flight) {
        // Push the flight into the queue depending on the unimped time
        if (headNode == null) {
            insertAtStart(flight);
        } else {
            Node currNode = headNode;
            Node newNode = new Node(flight);
            int count = 1;
            if (flight.getWheelOffTimestamp() < currNode.flight.getWheelOffTimestamp()) {
                insertAtStart(flight);
                recalculateAlphaRT(headNode);
            } else {
                while (currNode.nextNode != null && currNode.nextNode.flight.getWheelOffTimestamp() < flight.getWheelOffTimestamp()) {
                    currNode = currNode.nextNode;
                    count++;
                }

                long newWheelOffTimestamp = estimateWheelOffTimestamp(flight, count);
                newNode.flight.setWheelOffTimestamp(newWheelOffTimestamp);
                logger.debug("Wheel off changed to " + new Date(newWheelOffTimestamp) + " === " + flight);
                newNode.nextNode = currNode.nextNode;
                currNode.nextNode = newNode;
                recalculateAlphaRT(newNode);
            }
        }
    }

    public void recalculateAlphaRT(Node newNode) {
        Node currNode = newNode;
        while (currNode != null) {
            long newWheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(newNode.flight, Constants.alpha);
            currNode.flight.setWheelOffTimestamp(newWheelOffTimestamp);
            currNode = currNode.nextNode;
        }
    }
}
