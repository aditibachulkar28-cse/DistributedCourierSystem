package courier;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Node node;

    public ClientHandler(Socket socket, Node node) {

        this.socket = socket;
        this.node = node;
    }

    @Override
    public void run() {

        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            socket.getInputStream());

            Message message =
                    (Message) input.readObject();

            node.handleMessage(message);

            if (message.getType() == MessageType.GLOBAL_STATE) {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(node.describeState());
                output.flush();
                output.close();
            }

            input.close();
            socket.close();

        } catch (Exception e) {

            System.out.println(
                    "Communication error at Node " +
                    node.getNodeId());
        }
    }
}
