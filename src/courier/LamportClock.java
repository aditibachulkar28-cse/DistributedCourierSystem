package courier;

public class LamportClock {

    private int time = 0;

    public synchronized int tick() {
        time++;
        return time;
    }

    public synchronized int sendEvent() {
        time++;
        return time;
    }

    public synchronized int receiveEvent(int receivedTime) {
        time = Math.max(time, receivedTime);
        time++;
        return time;
    }

    public synchronized int getTime() {
        return time;
    }
}