# Syllabus Mapping

| Syllabus Concept | Status | File | Explanation |
|---|---|---|---|
| Clock synchronization | DEMONSTRATED | `clock/ClockSynchronizationDemo.java` | Berkeley-style average-time simulation |
| Logical clocks / Lamport | IMPLEMENTED | `LamportClock.java`, `clock/LamportDemo.java` | local, send and receive timestamps |
| Global state | IMPLEMENTED | `Node.java`, `CourierClient.java` | collected current states; not an atomic snapshot |
| Vector algorithm | IMPLEMENTED | `VectorClock.java`, `clock/VectorClockDemo.java` | merge then increment local entry |
| Election | IMPLEMENTED | `LeaderElection.java` | simplified reachable-highest-ID Bully style |
| Mutual exclusion | DEMONSTRATED | `MutualExclusion.java` | one-JVM Java monitor, not network distributed mutex |
| Beacon protocol | IMPLEMENTED | `BeaconProtocol.java` | TCP connection availability check |
| Lodha-Kshemkalyani | THEORY ONLY | `unit3/Lodha_Kshemkalyani_Fair_Mutual_Exclusion.md` | fair mutex study note |
| Knapp deadlock detection | THEORY ONLY | `unit3/Knapp_Distributed_Deadlock_Detection.md` | classification study note |
| Distributed objects | DEMONSTRATED | `unit4/DistributedObjectDemo.java` | local remote-style educational simulation |
| Distributed web systems | THEORY ONLY | `unit4/Distributed_Web_Based_Systems.md` | architecture note |
| Distributed file system | DEMONSTRATED | `unit4/DistributedFileSystemDemo.java` | storage/replica simulation |
| Serverless | THEORY ONLY | `unit4/Serverless_Architecture.md` | no cloud account required |
| Megaport / Cloudflare / AWS | THEORY ONLY | `unit4/case-studies/` | case-study notes |
| Hadoop / Kubernetes | THEORY ONLY | `unit4/case-studies/` | case-study notes |
| Blockchain / DLT | THEORY ONLY | `unit4/Blockchain_DLT.md` | study note |
| Database trade-offs | THEORY ONLY | `unit4/Distributed_Database_Tradeoffs.md` | CAP and design trade-offs |
