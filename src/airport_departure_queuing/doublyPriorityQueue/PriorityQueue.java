package airport_departure_queuing.doublyPriorityQueue;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.util.Constants;

import java.util.NoSuchElementException;

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
            throw new NoSuchElementException();
        }
        return list.removeFirst();
    }

    public Flight peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.getFirstNode().flight;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}