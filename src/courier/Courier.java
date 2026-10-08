package courier;

import java.io.Serializable;

public class Courier implements Serializable {

    private String courierId;
    private String sender;
    private String receiver;
    private String currentLocation;
    private String status;

    public Courier(String courierId, String sender, String receiver) {
        this.courierId = courierId;
        this.sender = sender;
        this.receiver = receiver;
        this.currentLocation = "Pune";
        this.status = "CREATED";
    }

    public String getCourierId() {
        return courierId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Courier ID: " + courierId +
                ", Sender: " + sender +
                ", Receiver: " + receiver +
                ", Location: " + currentLocation +
                ", Status: " + status;
    }
}