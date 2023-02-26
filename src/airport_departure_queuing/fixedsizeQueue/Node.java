/**

 Represents a node in a linked list used to implement a queue of flights departing from an airport.
 */
package airport_departure_queuing.fixedsizeQueue;

import airport_departure_queuing.flight.Flight;

public class Node {
    //this contains the data and the pointer to the next node in the linkedList 
    Flight flight;
    Node nextNode;

    //constructor
    /**
     * Constructs a new Node with the specified Flight object.
     * @param flight The Flight object to be stored in the Node.
     */
    public Node(Flight flight) {
        this.flight = flight;
        this.nextNode = null;
    }
    /**
     * Returns the Flight object stored in this Node.
     * @return The Flight object stored in this Node.
     */
    public Flight getFlight() {
        return flight;
    }
}
