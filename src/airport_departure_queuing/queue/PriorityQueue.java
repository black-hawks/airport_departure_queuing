package airport_departure_queuing.queue;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.util.Constants;
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
                recalculateAlphaRT(currNode);
            } else {
                while (currNode.nextNode != null && currNode.nextNode.flight.getWheelOffTimestamp() < flight.getWheelOffTimestamp()) {
                    currNode = currNode.nextNode;
                    count++;
                }

                long newWheelOffTimestamp = estimateWheelOffTimestamp(flight, Constants.alpha * count);
                logger.debug(newNode.flight.getAirline() + " Wheel off changed from "
                        + new Date(flight.getWheelOffTimestamp()) + " to " + new Date(newWheelOffTimestamp));
                newNode.flight.setWheelOffTimestamp(newWheelOffTimestamp);
                newNode.nextNode = currNode.nextNode;
                currNode.nextNode = newNode;
                recalculateAlphaRT(newNode.nextNode);
            }
        }
    }

    public void recalculateAlphaRT(Node newNode) {
        Node currNode = newNode;
        while (currNode != null) {
            long newWheelOffTimestamp = FlightEstimator.estimateWheelOffTimestamp(currNode.flight, Constants.alpha);
            logger.debug("Re - " + currNode.flight.getAirline() + " Wheel off changed from "
                    + new Date(currNode.flight.getWheelOffTimestamp()) + " to " + new Date(newWheelOffTimestamp));
            currNode.flight.setWheelOffTimestamp(newWheelOffTimestamp);
            currNode = currNode.nextNode;
        }
    }

    public String toString() {
        String result = "Queue{";
        Node currNode = headNode;
        while (currNode != null) {
            result += currNode.flight.getAirline() + ",";
            currNode = currNode.nextNode;
        }
        result += "}";
        return result;
    }
}
