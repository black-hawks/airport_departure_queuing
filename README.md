# A Queuing Model of the Airport Departure Process

The situation of airplanes having to wait in the runway queue for takeoff and landing has become a significant problem due to the required separation time between them. This waiting time leads to increased fuel consumption. To address this issue, an approach known as arrival/departure manager (AMAN/DMAN) has been suggested and put into operation at some of the world's major airports. However, the effectiveness of AMAN/DMAN is heavily dependent on the specific actions and algorithms it employs. Therefore, ongoing efforts to update and improve the algorithm are necessary to enhance the overall system's performance.

A queuing model for the airport departure process was suggested by Ioannis Simaiakis and Hamsa Balakrishnan at MIT. In this paper, they presented a queue-based approach through which we can observe the congestion at airport or for prediciting taxi-out time for individual flights.
The main focus of this paper is to anticipate airport performance on the assumption that the pushback schedules are already known. However, the paper does not explore how the uncertainty in the pushback schedules could affect the airport's performance. 

The aircraft to depart from the airport, it needs to travel through two queues.

First is taxiway queue. Once a aircraft is pushed back from the gates, it enters to ramp and taxiway queue. This is a dynamic queue and doesn’t have a fixed length. Once the aircraft enters the taxiway queue it goes through several stages of test which make it flight ready and then only starts heading to the depature queue. We consider this time along with the average time taken by aircraft to cover the from its gate to queue, to prioritize which aircraft enters the queue first.

The second queue is the departure queue, which is of finite length. If there is space available in the departure queue, then the aircraft with the highest priority in the taxiway queue leaves the taxiway queue and enters the departure queue. Aircraft in the departure queue are served on a First-Come-First-Served (FCFS) basis. If any aircraft ahead in this queue, could not depart from the runway at its expected time, it will delay the aircraft behind this. This is considered a queueing delay.

We record this queuing delay of each aircraft by subtracting the actual wheel-off time from the initially expected wheel-off time. The mean queueing delay gives the efficiency of the airport in handling the departure traffic. And this observation can be used to further reduce the congestion at the airport.

### Contributors:

Vivek Murarka (22200673)

Nikhitha Grace Josh (22200726)

Purvish Shah (22200112)

Ravi Raj Pedada (22200547)

Meghana Kamsetty Ravikumar (22200568)
