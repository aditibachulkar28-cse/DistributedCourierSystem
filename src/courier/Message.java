package courier;

import java.io.Serializable;

public class Message implements Serializable {

    private MessageType type;
    private int senderId;
    private int receiverId;
    private String courierId;
    private String data;
    private int lamportTime;
    private int[] vectorTime;

    public Message(
            MessageType type,
            int senderId,
            int receiverId,
            String courierId,
            String data,
            int lamportTime) {

        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.courierId = courierId;
        this.data = data;
        this.lamportTime = lamportTime;
        this.vectorTime = new int[] {0, 0, 0};
    }

    public Message(MessageType type, int senderId, int receiverId,
            String courierId, String data, int lamportTime, int[] vectorTime) {
        this(type, senderId, receiverId, courierId, data, lamportTime);
        setVectorTime(vectorTime);
    }

    public MessageType getType() {
        return type;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getCourierId() {
        return courierId;
    }

    public String getData() {
        return data;
    }

    public int getLamportTime() {
        return lamportTime;
    }

    public int[] getVectorTime() {
        return vectorTime == null ? new int[] {0, 0, 0} : vectorTime.clone();
    }

    public void setVectorTime(int[] vectorTime) {
        this.vectorTime = vectorTime == null ? new int[] {0, 0, 0} : vectorTime.clone();
    }
}
