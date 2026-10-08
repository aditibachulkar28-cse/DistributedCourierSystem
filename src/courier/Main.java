package courier;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc =
                new Scanner(System.in);

        System.out.println(
                "====================================");

        System.out.println(
                " DISTRIBUTED COURIER TRACKING SYSTEM");

        System.out.println(
                "====================================");

        System.out.println();

        System.out.println(
                "1. Start Pune Node");

        System.out.println(
                "2. Start Mumbai Node");

        System.out.println(
                "3. Start Delhi Node");

        System.out.print(
                "Enter Node ID: ");

        int nodeId = sc.nextInt();

        String location;

        switch (nodeId) {

            case 1:
                location = "Pune";
                break;

            case 2:
                location = "Mumbai";
                break;

            case 3:
                location = "Delhi";
                break;

            default:
                System.out.println(
                        "Invalid Node");

                return;
        }

        Node node =
                new Node(
                        nodeId,
                        location);

        node.start();
    }
}