package courier;

public enum MessageType {

    COURIER_CREATE,
    COURIER_TRANSFER,
    COURIER_UPDATE,
    QUERY_COURIER,

    ELECTION,
    OK,
    LEADER,

    REQUEST,
    REPLY,
    RELEASE,

    HEARTBEAT,
    GLOBAL_STATE
}
