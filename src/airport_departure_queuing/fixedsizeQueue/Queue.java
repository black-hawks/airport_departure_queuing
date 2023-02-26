package airport_departure_queuing.fixedsizeQueue;

import airport_departure_queuing.flight.Flight;

public class Queue {

    Node headNode;
    /**
     * Constructs an empty Queue object with a null headNode.
     */
    public Queue() {
        headNode = null;
    }

    /**
     * Inserts a new Flight object at the start of the Queue.
     * @param flight the Flight object to be inserted
     */
    public void insertAtStart(Flight flight) {
        Node newNode = new Node(flight);
        newNode.nextNode = headNode;
        headNode = newNode;
    }

    /**
     * Inserts a new Flight object at the end of the Queue.
     * @param flight the Flight object to be inserted
     */
    public void insertAtEnd(Flight flight) {
        if (headNode == null) {
            insertAtStart(flight);
        } else {
            Node currNode = headNode;
            while (currNode.nextNode != null) {
                currNode = currNode.nextNode;
            }
            Node newNode = new Node(flight);
            currNode.nextNode = newNode;
            newNode.nextNode = null;
        }
    }

    /**
     * Returns the Flight object at the head of the Queue without removing it.
     * @return the Flight object at the head of the Queue
     */
    public Flight peek() {
        if (headNode == null) {
            return null;
        }
        return headNode.flight;
    }

    /**
     * Deletes the Flight object at the head of the Queue.
     */
    public void deleteAtStart() {
        Node currNode = headNode;
        if (headNode != null) {
            headNode = currNode.nextNode;
        } else {
            System.out.println("There are no elements in the Queue.Queue");
        }
    }

    /**
     * Displays the contents of the Queue on the console.
     */
    public void display() {
        Node currNode = headNode;
        if (currNode == null) {
            System.out.println("List is Empty");
        } else {
            while (currNode.nextNode != null) {
                System.out.print(currNode.flight.getAirline() + " -> ");
                currNode = currNode.nextNode;
            }
            System.out.print(currNode.flight.getAirline());
        }
        System.out.println("\n");
    }

    /**
     * Returns a String representation of the Queue object.
     * @return a String representation of the Queue object
     */
    public String toString() {
        String result = this.getClass().getCanonicalName() + "{";
        Node currNode = headNode;
        while (currNode != null) {
            result += currNode.flight.getAirline() + ",";
            currNode = currNode.nextNode;
        }
        result += "}";
        return result;
    }

    /**
     * Returns the number of Flight objects in the Queue.
     * @return the number of Flight objects in the Queue
     */
    public int size() {
        int count = 0;
        Node currNode = headNode;
        while (currNode != null) {
            count++;
            currNode = currNode.nextNode;
        }
        return count;
    }
}