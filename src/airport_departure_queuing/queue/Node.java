package airport_departure_queuing.queue;

import airport_departure_queuing.flight.Flight;

public class Node {
    //this contains the data and the pointer to the next node in the linkedList 
    Flight flight;
    Node nextNode;

    //constructor
    public Node(Flight flight) {
        this.flight = flight;
        this.nextNode = null;
    }
}
