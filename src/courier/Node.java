package courier;

import java.io.*;
import java.net.*;
import java.util.*;

public class Node {

    private int nodeId;
    private String location;
    private int port;

    private LamportClock clock;
    private VectorClock vectorClock;

    private Map<String, Courier> couriers;

    private ServerSocket serverSocket;

    public Node(int nodeId, String location) {

        this.nodeId = nodeId;
        this.location = location;

        this.port = NodeConfig.getPort(nodeId);

        this.clock = new LamportClock();
        this.vectorClock = new VectorClock(nodeId);

        this.couriers = new HashMap<>();
    }

    public void start() {

        try {

            serverSocket = new ServerSocket(port);

            System.out.println("--------------------------------");
            System.out.println("Node " + nodeId + " started");
            System.out.println("Location : " + location);
            System.out.println("Port     : " + port);
            System.out.println("--------------------------------");

            while (true) {

                Socket socket = serverSocket.accept();

                ClientHandler handler =
                        new ClientHandler(socket, this);

                new Thread(handler).start();
            }

        } catch (IOException e) {

            System.out.println(
                    "Node " + nodeId +
                    " could not start.");

            System.out.println(
                    "Error: " + e.getMessage());

            e.printStackTrace();
        }
    }

    public synchronized void handleMessage(Message message) {

        int newTime =
                clock.receiveEvent(
                        message.getLamportTime());

        System.out.println(
                "\nMessage received at Node " +
                nodeId);

        System.out.println(
                "Lamport Clock = " + newTime);
        vectorClock.receiveEvent(message.getVectorTime());
        System.out.println("Vector Clock  = " + vectorClock.display());

        switch (message.getType()) {

            case COURIER_CREATE:
                createCourier(message);
                break;

            case COURIER_TRANSFER:
                receiveCourier(message);
                break;

            case QUERY_COURIER:
                showCourier(message.getCourierId());
                break;

            case HEARTBEAT:
                System.out.println("Heartbeat received at Node " + nodeId + ".");
                break;

            case GLOBAL_STATE:
                System.out.println("Global state requested from Node " + nodeId + ".");
                break;

            default:
                System.out.println(
                        "Message type: " +
                        message.getType());
        }
    }

    private void createCourier(Message message) {

        String[] data =
                message.getData().split(",");

        Courier courier =
                new Courier(
                        message.getCourierId(),
                        data[0],
                        data[1]);

        courier.setCurrentLocation(location);

        couriers.put(
                message.getCourierId(),
                courier);

        int time = clock.tick();
        int[] vectorTime = vectorClock.tick();

        CourierEvent event =
                new CourierEvent(
                        message.getCourierId(),
                        "COURIER_CREATED",
                        location,
                        time,
                nodeId,
                vectorTime);

        EventLogger.log(event);

        System.out.println(event);
    }

    private void receiveCourier(Message message) {

        String[] data =
                message.getData().split(",");

        Courier courier =
                new Courier(
                        message.getCourierId(),
                        data[0],
                        data[1]);

        courier.setCurrentLocation(location);

        courier.setStatus("IN_TRANSIT");

        couriers.put(
                message.getCourierId(),
                courier);

        int time = clock.tick();
        int[] vectorTime = vectorClock.tick();

        CourierEvent event =
                new CourierEvent(
                        message.getCourierId(),
                        "COURIER_RECEIVED",
                        location,
                        time,
                nodeId,
                vectorTime);

        EventLogger.log(event);

        System.out.println(event);
    }

    public synchronized void showCourier(
            String courierId) {

        Courier courier =
                couriers.get(courierId);

        if (courier == null) {

            System.out.println(
                    "Courier not found at Node " +
                    nodeId);

        } else {

            System.out.println(courier);
        }
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getLocation() {
        return location;
    }

    public LamportClock getClock() {
        return clock;
    }

    public VectorClock getVectorClock() {
        return vectorClock;
    }

    public Map<String, Courier> getCouriers() {
        return couriers;
    }

    /** Text form used by the client to demonstrate the combined global state. */
    public synchronized String describeState() {
        StringBuilder state = new StringBuilder();
        state.append("Node ").append(nodeId).append(" - ").append(location).append('\n');
        state.append("Status: ALIVE\n");
        state.append("Lamport Clock: ").append(clock.getTime()).append('\n');
        state.append("Vector Clock: ").append(vectorClock.display()).append('\n');
        state.append("Couriers:\n");
        if (couriers.isEmpty()) {
            state.append("  No courier stored at this node.\n");
        } else {
            for (Courier courier : couriers.values()) {
                state.append("  ").append(courier).append('\n');
            }
        }
        return state.toString();
    }
}
