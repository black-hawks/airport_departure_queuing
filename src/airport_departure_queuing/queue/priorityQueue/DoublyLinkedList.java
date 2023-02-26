/**

 Represents a doubly linked list of Flight objects, ordered by priority.
 */
package airport_departure_queuing.queue.priorityQueue;

import airport_departure_queuing.flight.Flight;

import java.util.NoSuchElementException;

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a new Flight object to the front of the doubly linked list.
     *
     * @param flight the Flight object to add
     */
    public void addFirst(Flight flight) {
        Node newNode = new Node(flight, head, null);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }


    /**
     * Inserts a new Flight object after a given node in the doubly linked list.
     *
     * @param node the node after which to insert the new Flight object
     * @param flight the Flight object to insert
     */
    public void insertAfter(Node node, Flight flight) {
        Node newNode = new Node(flight, node.next, node);
        node.next = newNode;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        size++;
    }



    /**
     * Removes and returns the Flight object at the front of the doubly linked list.
     *
     * @return the Flight object at the front of the doubly linked list
     * @throws NoSuchElementException if the list is empty
     */
    public Flight removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Flight flight = head.flight;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return flight;
    }

    /**
     * Returns the first node of the doubly linked list.
     *
     * @return the first node of the doubly linked list
     */
    public Node getFirstNode() {
        return head;
    }

    /**
     * Returns the last node of the doubly linked list.
     *
     * @return the last node of the doubly linked list
     */
    public Node getLastNode() {
        return tail;
    }

    /**
     * Returns whether the doubly linked list is empty or not.
     *
     * @return true if the doubly linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the doubly linked list.
     *
     * @return the size of the doubly linked list
     */
    public int size() {
        return size;
    }
}
