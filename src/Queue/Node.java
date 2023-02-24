package Queue;

import airport_departure_queuing.Flight;

import java.util.Date;

public class Node {
    //this contains the data and the pointer to the next node in the linkedList 
    Flight flight;
    Date flightEstimate;
    Node nextNode;
    //constructor
    public Node(Flight flight, Date flightEstimate){
        this.flight = flight;
        this.flightEstimate = flightEstimate;
        this.nextNode = null;
    }
}
