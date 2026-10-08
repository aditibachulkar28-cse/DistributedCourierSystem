package courier;

public class MutualExclusion {

    private boolean locked = false;

    public synchronized void requestAccess(int nodeId) {

        while (locked) {

            try {
                System.out.println(
                        "Node " + nodeId +
                        " is waiting for the critical section.");

                wait();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        locked = true;

        System.out.println(
                "Node " + nodeId +
                " entered critical section.");
    }

    public synchronized void releaseAccess(int nodeId) {

        locked = false;

        System.out.println(
                "Node " + nodeId +
                " released critical section.");

        notifyAll();
    }

    // Test Mutual Exclusion
    public static void main(String[] args) {

        System.out.println(
                "====================================");

        System.out.println(
                "   DISTRIBUTED MUTUAL EXCLUSION");

        System.out.println(
                "====================================");

        MutualExclusion mutex =
                new MutualExclusion();

        Thread node1 = new Thread(() -> {

            mutex.requestAccess(1);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(
                    "Node 1 is performing critical-section work.");

            mutex.releaseAccess(1);

        });

        Thread node2 = new Thread(() -> {

            mutex.requestAccess(2);

            System.out.println(
                    "Node 2 is performing critical-section work.");

            mutex.releaseAccess(2);

        });

        node1.start();
        node2.start();
    }
}