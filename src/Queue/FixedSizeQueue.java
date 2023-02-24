package Queue;

import airport_departure_queuing.Flight;
import airport_departure_queuing.FlightEstimator;

import java.util.Calendar;
import java.util.Date;

public class FixedSizeQueue extends Queue {
    int size;
    int currentQueueLength;

    public FixedSizeQueue(int size) {
      super();
      this.size = size;
      this.currentQueueLength = 0;
    }

    // if size of queue is exceeded, then say sorry queue full
    // if the one being inserted and the current first node has same time, then add 1 minute to the node being inserted
    public boolean insertAtEnd(Flight flight, Date estimateTakeoffTime){
      if(currentQueueLength >= size) {
        return false;
      }

      Node currNode = headNode;
      Node newNode = new Node(flight, estimateTakeoffTime);
      if(headNode == null){
        insertAtStart(flight, estimateTakeoffTime);
      }
      else {
        while(currNode.nextNode != null){
          currNode = currNode.nextNode;
        }
        currNode.nextNode = newNode;
        newNode.nextNode = null;
      }
      Date newFlightEstimate = new FlightEstimator().getEstimateTakeOffTime(flight);
      Date previousFlightEstimate = new FlightEstimator().getEstimateTakeOffTime(currNode.flight);
      if(newFlightEstimate == previousFlightEstimate) {
        Calendar time = Calendar.getInstance();
        time.add(newNode.flightEstimate, 10);
        newNode.flightEstimate = time.getTime();
      }
      currentQueueLength++;
      return true;
    }
}
