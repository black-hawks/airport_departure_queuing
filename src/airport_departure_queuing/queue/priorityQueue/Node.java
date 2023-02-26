/**

 Represents a node in a doubly linked list.
 */
package airport_departure_queuing.queue.priorityQueue;

import airport_departure_queuing.flight.Flight;

class Node {
    Flight flight;
    Node next;
    Node prev;

    /**

     Constructs a new node with the given flight, next node, and previous node.
     @param flight the flight associated with this node
     @param next the next node in the list
     @param prev the previous node in the list
     */
    public Node(Flight flight, Node next, Node prev) {
        this.flight = flight;
        this.next = next;
        this.prev = prev;
    }
}
