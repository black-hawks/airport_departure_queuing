/**

 A priority queue that stores flights in a doubly linked list and sorts them based on their actual wheel-off timestamp.
 The queue is sorted in descending order, i.e., the flight with the latest actual wheel-off timestamp has the highest priority.
 */
package airport_departure_queuing.queue.priorityQueue;

import airport_departure_queuing.flight.Flight;
import airport_departure_queuing.flight.FlightEstimator;
import airport_departure_queuing.util.Constants;

public class PriorityQueue {

    private final DoublyLinkedList list;

    /**
     * Creates a new priority queue with an empty list.
     */
    public PriorityQueue() {
        list = new DoublyLinkedList();
    }

    /**
     * Adds a flight to the priority queue based on its actual wheel-off timestamp.
     * The flight is inserted before the first flight with a later actual wheel-off timestamp.
     * The actual wheel-off timestamp of each flight in the queue is updated to account for the new flight.
     *
     * @param flight the flight to be added
     */
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

    /**
     * Removes and returns the flight with the highest priority (i.e., the flight with the latest actual wheel-off timestamp).
     * If the queue is empty, null is returned.
     *
     * @return the flight with the highest priority, or null if the queue is empty
     */
    public Flight dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }
    /**
     * Returns the flight with the highest priority (i.e., the flight with the latest actual wheel-off timestamp)
     * without removing it from the queue.
     * If the queue is empty, null is returned.
     *
     * @return the flight with the highest priority, or null if the queue is empty
     */
    public Flight peek() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirstNode().flight;
    }

    /**
     * Returns the number of flights in the priority queue.
     *
     * @return the number of flights in the priority queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if the priority queue is empty, false otherwise.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}