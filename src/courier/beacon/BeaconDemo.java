package courier.beacon;

import courier.BeaconProtocol;

public class BeaconDemo {
    public static void main(String[] args) {
        System.out.println("Start one or more nodes first.");
        BeaconProtocol.checkAllNodes();
    }
}
