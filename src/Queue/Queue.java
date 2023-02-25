package Queue;

import airport_departure_queuing.Flight;

import java.util.Date;

public class Queue {

  Node headNode;

  public Queue() {
    headNode = null;
  }

  public void insertAtStart(Flight flight, Date estimateTakeoffTime) {
    Node newNode = new Node(flight, estimateTakeoffTime);
    newNode.nextNode = headNode;
    headNode = newNode;
  }

  public void insertAtEnd(Flight flight, Date estimateTakeoffTime){
    if(headNode == null){
      insertAtStart(flight, estimateTakeoffTime);
    }
    else{
      Node currNode = headNode;
      while(currNode.nextNode != null){
        currNode = currNode.nextNode;
      }
      Node newNode = new Node(flight, estimateTakeoffTime);
      currNode.nextNode = newNode;
      newNode.nextNode = null;
    }
  }

  public Flight peek() {
    return headNode.flight;
  }

  public void deleteAtStart(){
    if(headNode!=null){
      Node currNode = headNode;
      headNode = currNode.nextNode;
    } else {
      System.out.println("There are no elements in the Queue.Queue");
    }
  }

  public void display() {
    Node currNode= headNode;
    if(currNode == null) {
      System.out.println("List is Empty");
    } else {
      while(currNode.nextNode != null) {
        System.out.print(currNode.flight.getFlightNumber() + " -> ");
        currNode = currNode.nextNode;
      }
      System.out.print(currNode.flight.getFlightNumber());
    }
    System.out.println("\n");
  }
}