package courier;

import java.io.FileWriter;
import java.io.IOException;

public class EventLogger {

    private static final String FILE = "data/events.txt";

    public static synchronized void log(CourierEvent event) {

        try {

            FileWriter writer = new FileWriter(FILE, true);

            writer.write(event.toString());
            writer.write(System.lineSeparator());

            writer.close();

        } catch (IOException e) {

            System.out.println("Unable to write event log.");
            e.printStackTrace();
        }
    }
}