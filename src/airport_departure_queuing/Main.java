package airport_departure_queuing;

import java.time.Duration;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date currentTime = new Date();
        Duration timeIncrement = Duration.ofMinutes(1);
        Simulator simulator = new Simulator(currentTime, timeIncrement);
        while (true) {
            simulator.simulateTime();
            System.out.println(simulator.currentTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
