package courier;

import java.util.HashMap;
import java.util.Map;

public class NodeConfig {

    public static final Map<Integer, Integer> PORTS = new HashMap<>();

    static {
        PORTS.put(1, 5001);
        PORTS.put(2, 5002);
        PORTS.put(3, 5003);
    }

    public static int getPort(int nodeId) {
        return PORTS.get(nodeId);
    }

    public static String getLocation(int nodeId) {
        switch (nodeId) {
            case 1: return "Pune";
            case 2: return "Mumbai";
            case 3: return "Delhi";
            default: return "Unknown";
        }
    }
}
