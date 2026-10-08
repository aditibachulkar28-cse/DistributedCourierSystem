package courier.clock;

import courier.LamportClock;

public class LamportDemo {
    public static void main(String[] args) {
        LamportClock pune = new LamportClock();
        LamportClock mumbai = new LamportClock();
        int sent = pune.sendEvent();
        System.out.println("Pune local/send event: " + sent);
        System.out.println("Mumbai receives timestamp " + sent + ", becomes: " + mumbai.receiveEvent(sent));
        System.out.println("Mumbai local event: " + mumbai.tick());
        System.out.println("Rule on receive: max(local, received) + 1.");
    }
}
