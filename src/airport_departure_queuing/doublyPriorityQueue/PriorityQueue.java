package airport_departure_queuing.doublyPriorityQueue;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.util.Constants;

public class PriorityQueue {

    private final DoublyLinkedList list;

    public PriorityQueue() {
        list = new DoublyLinkedList();
    }

    public void enqueue(Flight flight) {
        int position = size();
        Node current = list.getLastNode();
        while (current != null && current.flight.getActualWheelOffTimestamp() > flight.getActualWheelOffTimestamp()) {
            current.flight.setActualWheelOffTimestamp(
                    FlightEstimator.estimateWheelOffTimestamp(current.flight, Constants.alpha)
            );
            current = current.prev;
            position--;
        }
        flight.setActualWheelOffTimestamp(
                FlightEstimator.estimateWheelOffTimestamp(flight, Constants.alpha * (position))
        );
        if (current != null) {
            list.insertAfter(current, flight);
        } else {
            list.addFirst(flight);
        }
    }

    public Flight dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public Flight peek() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirstNode().flight;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = list.getFirstNode();
        while (current != null) {
            sb.append(current.flight.getAirline());
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}