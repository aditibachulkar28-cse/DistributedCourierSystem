package courier.clock;

import courier.VectorClock;
import java.util.Arrays;

public class VectorClockDemo {
    public static void main(String[] args) {
        VectorClock node1 = new VectorClock(1);
        VectorClock node2 = new VectorClock(2);
        VectorClock node3 = new VectorClock(3);
        int[] one = node1.sendEvent();
        int[] two = node2.receiveEvent(one);
        int[] three = node3.receiveEvent(two);
        System.out.println("Node 1 event: " + Arrays.toString(one));
        System.out.println("Node 2 receives Node 1: " + Arrays.toString(two));
        System.out.println("Node 3 receives Node 2: " + Arrays.toString(three));
        System.out.println("[1,0,0] happened-before [1,1,0]; neither [1,0,0] nor [0,1,0] dominates the other, so they are concurrent.");
    }
}
