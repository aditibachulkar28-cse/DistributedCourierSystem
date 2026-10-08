package courier;

import java.util.Scanner;

public class CourierClient {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println(" DISTRIBUTED COURIER CLIENT");
        System.out.println("====================================");

        System.out.println();
        System.out.println("1. Create Courier");
        System.out.println("2. Transfer Courier");
        System.out.println("3. Query Courier");
        System.out.println("4. Show Global State");
        System.out.println("5. Check Beacon Protocol");
        System.out.println("6. Run Leader Election");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:

                System.out.print("Courier ID: ");
                String courierId = sc.nextLine();

                System.out.print("Sender: ");
                String sender = sc.nextLine();

                System.out.print("Receiver: ");
                String receiver = sc.nextLine();

                System.out.print("Send to Node: ");
                int node = sc.nextInt();

                Message createMessage =
                        new Message(
                                MessageType.COURIER_CREATE,
                                0,
                                node,
                                courierId,
                                sender + "," + receiver,
                                1);

                MessageSender.send(createMessage);

                System.out.println(
                        "Courier creation request sent.");

                break;

            case 2:

                System.out.print("Courier ID: ");
                courierId = sc.nextLine();

                System.out.print("Sender: ");
                sender = sc.nextLine();

                System.out.print("Receiver: ");
                receiver = sc.nextLine();

                System.out.print("Transfer to Node: ");
                node = sc.nextInt();

                Message transferMessage =
                        new Message(
                                MessageType.COURIER_TRANSFER,
                                0,
                                node,
                                courierId,
                                sender + "," + receiver,
                                1);

                MessageSender.send(transferMessage);

                System.out.println(
                        "Courier transfer request sent.");

                break;

            case 3:

                System.out.print("Courier ID: ");
                courierId = sc.nextLine();

                System.out.print("Query Node: ");
                node = sc.nextInt();

                Message queryMessage =
                        new Message(
                                MessageType.QUERY_COURIER,
                                0,
                                node,
                                courierId,
                                "",
                                1);

                MessageSender.send(queryMessage);

                System.out.println(
                        "Courier query request sent.");

                break;

            case 4:
                System.out.println("\n========== GLOBAL STATE ==========");
                System.out.println("Simple combined-state demonstration; not a Chandy-Lamport snapshot.\n");
                int highestAvailableNode = -1;
                for (int nodeId = 1; nodeId <= 3; nodeId++) {
                    Message stateRequest = new Message(MessageType.GLOBAL_STATE,
                            0, nodeId, null, "GLOBAL_STATE", 0);
                    String state = MessageSender.requestState(stateRequest);
                    System.out.println(state);
                    if (!state.contains("UNAVAILABLE")) {
                        highestAvailableNode = nodeId;
                    }
                }
                if (highestAvailableNode != -1) {
                    System.out.println("Leader information (simplified Bully rule): "
                            + "Node " + highestAvailableNode + " is the highest available node.");
                }
                System.out.println("==================================");
                break;

            case 5:
                BeaconProtocol.checkAllNodes();
                break;

            case 6:
                LeaderElection election = new LeaderElection(1);
                election.startElection();
                break;

            default:

                System.out.println(
                        "Invalid choice.");
        }

        sc.close();
    }
}
