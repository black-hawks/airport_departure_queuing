package airport_departure_queuing.doublyPriorityQueue;

import airport_departure_queuing.flight.Flight;

import java.util.NoSuchElementException;

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

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

//    public void addLast(Flight flight) {
//        Node newNode = new Node(flight, null, tail);
//        if (tail != null) {
//            tail.next = newNode;
//        }
//        tail = newNode;
//        if (head == null) {
//            head = newNode;
//        }
//        size++;
//    }

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


//    public void insertBefore(Node node, Flight flight) {
//        Node newNode = new Node(flight, node, node.prev);
//        node.prev = newNode;
//        if (newNode.prev != null) {
//            newNode.prev.next = newNode;
//        } else {
//            head = newNode;
//        }
//        size++;
//    }

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

    public Node getFirstNode() {
        return head;
    }

    public Node getLastNode() {
        return tail;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
