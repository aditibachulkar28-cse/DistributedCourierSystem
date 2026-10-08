# Test Plan

1. Compile all sources: `run_compile.bat`. Expected: no compiler errors.
2. Run `run_pune.bat`, `run_mumbai.bat`, and `run_delhi.bat`; enter 1, 2 and 3. Expected: ports 5001, 5002 and 5003 start.
3. Run `run_client.bat`, choose 1, create `C101` at node 1. Expected: Pune prints `COURIER_CREATED`, Lamport and vector timestamps; an event is appended to `data/events.txt`.
4. Run the client twice with choice 2: send C101 to node 2, then node 3. Expected: Mumbai and Delhi print `COURIER_RECEIVED`.
5. Choose 3 and query C101 at node 3. Expected: courier ID, sender, receiver, Delhi location and IN_TRANSIT status.
6. Choose 4. Expected: **GLOBAL STATE** contains all live nodes, their couriers and both clock values.
7. Choose 5. Expected: all running nodes are `ALIVE`. Stop Delhi with Ctrl+C and rerun; Node 3 is `UNAVAILABLE`.
8. Choose 6. Expected with all nodes running: Node 3 becomes leader. With Delhi stopped: Node 2 becomes leader.
9. Run `java -cp out courier.MutualExclusion`. Expected: Node 2 waits until Node 1 releases the critical section.
10. Run `java -cp out courier.clock.ClockSynchronizationDemo`. Expected: three different simulated times adjust to their average.
11. Run Lamport and vector demos. Expected: Lamport uses `max(local, received)+1`; vectors show `[1,0,0]`, `[1,1,0]`, `[1,1,1]`.
12. Run the object and DFS simulations. Expected: explanatory output only; no external system is required.

Do not expect an exact timestamp because clocks grow with every message. Global state is deliberately labelled a demonstration, not a full Chandy-Lamport snapshot.
