package courier;

/** Simple heartbeat/reachability check using the existing TCP ports. */
public class BeaconProtocol {

    public static void checkAllNodes() {
        System.out.println("\n========== BEACON STATUS ==========");
        for (int nodeId = 1; nodeId <= 3; nodeId++) {
            Message heartbeat = new Message(MessageType.HEARTBEAT, 0, nodeId,
                    null, "HEARTBEAT", 0);
            boolean alive = MessageSender.send(heartbeat);
            System.out.println("Node " + nodeId + " - "
                    + NodeConfig.getLocation(nodeId) + " - "
                    + (alive ? "ALIVE" : "UNAVAILABLE"));
        }
        System.out.println("===================================");
    }
}
