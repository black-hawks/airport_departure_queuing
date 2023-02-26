package airport_departure_queuing.queue;

import airport_departure_queuing.flight.Flight;

public class Queue {

    Node headNode;

    public Queue() {
        headNode = null;
    }

    public void insertAtStart(Flight flight) {
        Node newNode = new Node(flight);
        newNode.nextNode = headNode;
        headNode = newNode;
    }

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

    public Flight peek() {
        if (headNode == null) {
            return null;
        }
        return headNode.flight;
    }

    public void deleteAtStart() {
        Node currNode = headNode;
        if (headNode != null) {
            headNode = currNode.nextNode;
        } else {
            System.out.println("There are no elements in the Queue.Queue");
        }
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = headNode;
        while (current != null) {
            sb.append(current.flight.getAirline());
            current = current.nextNode;
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

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