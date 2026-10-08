package courier;

import java.io.Serializable;

public class CourierEvent implements Serializable {

    private String courierId;
    private String event;
    private String location;
    private int lamportTime;
    private int nodeId;
    private int[] vectorTime;

    public CourierEvent(
            String courierId,
            String event,
            String location,
            int lamportTime,
            int nodeId) {

        this.courierId = courierId;
        this.event = event;
        this.location = location;
        this.lamportTime = lamportTime;
        this.nodeId = nodeId;
        this.vectorTime = null;
    }

    public CourierEvent(String courierId, String event, String location,
            int lamportTime, int nodeId, int[] vectorTime) {
        this(courierId, event, location, lamportTime, nodeId);
        this.vectorTime = vectorTime == null ? null : vectorTime.clone();
    }

    @Override
    public String toString() {
        return "[Lamport=" + lamportTime +
                "] Node=" + nodeId +
                " Courier=" + courierId +
                " Event=" + event +
                " Location=" + location
                + (vectorTime == null ? "" : " Vector="
                + java.util.Arrays.toString(vectorTime));
    }
}
