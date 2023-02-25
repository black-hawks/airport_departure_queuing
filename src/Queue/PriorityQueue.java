package Queue;

import airport_departure_queuing.Flight;
import airport_departure_queuing.FlightEstimator;

import java.util.Date;

public class PriorityQueue extends Queue {
  final static double ALPHA = 0.2;

  public PriorityQueue() {
    super();
  }

  public void addFlight(Flight flight, Date estimateTakeoffTime) {
    // Push the flight into the queue depending on the unimped time
    if(headNode == null) {
      insertAtStart(flight, estimateTakeoffTime);
    } else {
      Node currNode = headNode;
      int length =0;

      Node newNode = new Node(flight, estimateTakeoffTime));
      currNode = headNode;
      int count = 1;
      if(flight.getUnimpededTime() < currNode.flight.getUnimpededTime()) {
        insertAtStart(flight, estimateTakeoffTime);
        recalculateAlphaRT(headNode);
      } else {
        while (currNode.nextNode != null && currNode.nextNode.flight.getUnimpededTime() < flight.getUnimpededTime()) {
          currNode = currNode.nextNode;
          count++;
        }

        newNode.flight.setTotalTravelTime(flight.getUnimpededTime() + (ALPHA * count));
        newNode.nextNode = currNode.nextNode;
        currNode.nextNode = newNode;
        recalculateAlphaRT(newNode);
      }
    }
  }

  public void recalculateAlphaRT(Node newNode) {
    Node currNode = newNode;
    while(currNode != null) {
      double totalTravelTime = currNode.flight.getUnimpededTime() + ALPHA;
      currNode.flight.setTotalTravelTime(totalTravelTime);
    }
  }
}
