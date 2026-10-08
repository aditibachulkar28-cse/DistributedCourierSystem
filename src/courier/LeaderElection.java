package courier;

public class LeaderElection {

    private int nodeId;
    private int leaderId = -1;

    public LeaderElection(int nodeId) {
        this.nodeId = nodeId;
    }

    public void startElection() {

        System.out.println("\nNode " + nodeId + " started election.");

        boolean higherNodeFound = false;
        int highestActiveNode = nodeId;

        // Check all higher-numbered nodes
        for (int i = nodeId + 1; i <= 3; i++) {

            try {

                Message message =
                        new Message(
                                MessageType.ELECTION,
                                nodeId,
                                i,
                                null,
                                "ELECTION",
                                0);

                if (MessageSender.send(message)) {

                    higherNodeFound = true;
                    highestActiveNode = i;

                    System.out.println(
                            "Node " + i + " is alive.");

                } else {

                    System.out.println(
                            "Node " + i + " is unavailable.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Node " + i + " is unavailable.");
            }
        }

        // Highest active node becomes leader
        leaderId = highestActiveNode;

        System.out.println(
                "\n====================================");

        System.out.println(
                "ELECTION RESULT");

        System.out.println(
                "Node " + leaderId + " becomes LEADER.");

        System.out.println(
                "====================================");
    }

    public int getLeaderId() {
        return leaderId;
    }

    public static void main(String[] args) {

        System.out.println(
                "====================================");

        System.out.println(
                "       BULLY LEADER ELECTION");

        System.out.println(
                "====================================");

        System.out.println(
                "Active Nodes: 1, 2, 3");

        LeaderElection election =
                new LeaderElection(1);

        election.startElection();

        System.out.println(
                "\nFinal Leader = Node " +
                election.getLeaderId());
    }
}