package courier.election;

import courier.LeaderElection;

public class BullyElectionDemo {
    public static void main(String[] args) {
        System.out.println("Start nodes first, then run this simplified Bully-style demo.");
        LeaderElection election = new LeaderElection(1);
        election.startElection();
    }
}
