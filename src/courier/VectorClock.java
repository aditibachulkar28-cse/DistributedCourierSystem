package courier;

import java.util.Arrays;

/** A three-entry vector clock: [Pune, Mumbai, Delhi]. */
public class VectorClock {

    private final int[] time = new int[3];
    private final int nodeId;

    public VectorClock(int nodeId) {
        this.nodeId = nodeId;
    }

    public synchronized int[] tick() {
        time[nodeId - 1]++;
        return getTime();
    }

    public synchronized int[] sendEvent() {
        return tick();
    }

    public synchronized int[] receiveEvent(int[] receivedTime) {
        if (receivedTime != null) {
            for (int i = 0; i < time.length && i < receivedTime.length; i++) {
                time[i] = Math.max(time[i], receivedTime[i]);
            }
        }
        time[nodeId - 1]++;
        return getTime();
    }

    public synchronized int[] getTime() {
        return Arrays.copyOf(time, time.length);
    }

    public synchronized String display() {
        return Arrays.toString(time);
    }
}
