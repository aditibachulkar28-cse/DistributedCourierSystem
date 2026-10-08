package courier;

import java.io.*;
import java.net.*;

public class MessageSender {

    public static boolean send(
            Message message) {

        try {

            int port =
                    NodeConfig.getPort(
                            message.getReceiverId());

            Socket socket =
                    new Socket(
                            "localhost",
                            port);

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            socket.getOutputStream());

            output.writeObject(message);
            output.flush();

            output.close();
            socket.close();

            System.out.println(
                    "Message sent from Node " +
                    message.getSenderId() +
                    " to Node " +
                    message.getReceiverId());

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Node " +
                    message.getReceiverId() +
                    " is unavailable.");

            return false;
        }
    }

    /** Sends a state request and returns the text produced by that node. */
    public static String requestState(Message message) {
        try {
            Socket socket = new Socket("localhost",
                    NodeConfig.getPort(message.getReceiverId()));
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(message);
            output.flush();

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String state = (String) input.readObject();
            input.close();
            output.close();
            socket.close();
            return state;
        } catch (Exception e) {
            return "Node " + message.getReceiverId() + " - "
                    + NodeConfig.getLocation(message.getReceiverId())
                    + "\nStatus: UNAVAILABLE\n";
        }
    }
}
