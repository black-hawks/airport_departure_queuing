package airport_departure_queuing.doublyPriorityQueue;

import airport_departure_queuing.flight.Flight;

class Node {
    Flight flight;
    Node next;
    Node prev;

    public Node(Flight flight, Node next, Node prev) {
        this.flight = flight;
        this.next = next;
        this.prev = prev;
    }
}
