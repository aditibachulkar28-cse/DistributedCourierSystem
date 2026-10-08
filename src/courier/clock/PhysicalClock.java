package courier.clock;

/** A simulated clock used only for the clock-synchronization lesson. */
public class PhysicalClock {
    private final int nodeId;
    private int seconds;

    public PhysicalClock(int nodeId, int seconds) {
        this.nodeId = nodeId;
        this.seconds = seconds;
    }

    public int getSeconds() { return seconds; }

    public void adjustBy(int offset) { seconds += offset; }

    public String display() {
        return String.format("Node %d clock = 10:00:%02d", nodeId, seconds);
    }
}
