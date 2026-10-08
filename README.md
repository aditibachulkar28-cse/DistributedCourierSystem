# Distributed Courier Tracking System

## What this project demonstrates

This is a small Java/TCP distributed-systems demonstration. Pune (node 1, port 5001), Mumbai (node 2, port 5002), and Delhi (node 3, port 5003) run in separate terminals. A client sends courier requests to them.

The new work keeps the original Lamport clock, courier workflow, event log, leader-election demo, and mutual-exclusion demo. It adds a three-entry vector clock, combined global-state view, and heartbeat availability check.

### Architecture

`CourierClient -> TCP Message -> Node -> Courier map / clocks / events.txt`

Each Node has a server socket and creates a `ClientHandler` thread for every incoming connection. `MessageSender` opens a TCP connection to the requested node. Global state is the one request that has a reply: the client asks every live node for its local state and prints the three answers together.

## Build and run

From the project folder:

```powershell
javac -d out src\courier\*.java
```

The command above keeps the original courier application build command. To compile the original application **and every independent teaching demo**, run `run_compile.bat`.

Open three terminals and run this in each:

```powershell
java -cp out courier.Main
```

Enter `1` in the first terminal (Pune), `2` in the second (Mumbai), and `3` in the third (Delhi).

Open a fourth terminal for each client operation:

```powershell
java -cp out courier.CourierClient
```

For the standalone mutual-exclusion demo:

```powershell
java -cp out courier.MutualExclusion
```

## Standalone demonstrations

After running `run_compile.bat`, use these commands. They do not alter the courier workflow.

```powershell
java -cp out courier.clock.ClockSynchronizationDemo
java -cp out courier.clock.LamportDemo
java -cp out courier.clock.VectorClockDemo
java -cp out courier.mutex.MutualExclusionDemo
java -cp out courier.unit4.DistributedObjectDemo
java -cp out courier.unit4.DistributedFileSystemDemo
```

Start one or more project nodes before these two TCP-based demos:

```powershell
java -cp out courier.beacon.BeaconDemo
java -cp out courier.election.BullyElectionDemo
```

`ClockSynchronizationDemo` is a Berkeley-style average-time simulation. It deliberately does not change the computer's operating-system clock. `LamportDemo` shows local/send/receive logical ordering. `VectorClockDemo` shows `[1,0,0]`, `[1,1,0]`, and `[1,1,1]`, plus happened-before and concurrency.

## Complete C101 demonstration

1. In CourierClient choose `1`, enter `C101`, a sender name, a receiver name, and node `1`. Pune prints `COURIER_CREATED`, a Lamport time, and vector time.
2. Start CourierClient again. Choose `2`, enter `C101`, the same sender/receiver, and node `2`. Mumbai prints `COURIER_RECEIVED` with its clocks.
3. Repeat choice `2`, destination node `3`. Delhi prints the receipt event.
4. Choose `3`, enter `C101`, query node `3`. Delhi prints the courier details.
5. Choose `4`. The client prints all three nodes, their couriers, Lamport clocks, and vector clocks.
6. Choose `5`. With all terminals open, all nodes report `ALIVE`. Stop Delhi with Ctrl+C and repeat: node 3 reports `UNAVAILABLE`.
7. Choose `6`. With all nodes alive the result is Node 3. Stop Delhi and run it again: Node 2 becomes leader.
8. Run `MutualExclusion` in another terminal. Node 2 waits while Node 1 holds the critical section, then enters only after Node 1 releases it.

Typical vector values are not fixed because they depend on earlier messages. A creation at Pune gives Pune a positive first entry; a subsequent receipt at Mumbai merges Pune's vector and increments Mumbai's own entry, for example `[2, 2, 0]`. A later Delhi receipt similarly has a positive third entry.

## Classes

| Class | Purpose |
|---|---|
| `Main`, `NodeConfig` | starts a selected node; fixes node IDs, locations and ports |
| `Node`, `ClientHandler` | TCP server and incoming-message processing |
| `Message`, `MessageType`, `MessageSender` | serializable protocol and TCP sending |
| `Courier`, `CourierClient` | courier data and interactive requests |
| `LamportClock`, `VectorClock` | scalar and vector logical clocks |
| `CourierEvent`, `EventLogger` | append-only event records in `data/events.txt` |
| `BeaconProtocol` | sends a small heartbeat to each configured node |
| `LeaderElection` | simplified reachability-based Bully-style election |
| `MutualExclusion` | one-process/thread critical-section demonstration |

## Unit III syllabus mapping

| Concept | Status | How demonstrated |
|---|---|---|
| Clock synchronization | Explained | Logical rather than physical-clock synchronization |
| Logical clock | Implemented | `LamportClock` updates on receive and local events |
| Lamport algorithm | Implemented | Lamport timestamps in terminal and event log |
| Global state | Implemented (simple) | client collects current local states; not Chandy-Lamport snapshot |
| Vector algorithm | Implemented | `VectorClock` merges received vectors and increments local position |
| Election algorithm | Implemented (simplified) | highest reachable node ID is leader |
| Mutual exclusion | Implemented (local demo) | synchronized lock permits one thread at a time |
| Beacon protocol | Implemented | TCP heartbeat connection checks node availability |
| Lodha & Kshemkalyani | Theory | viva notes below |
| Knapp deadlock classification | Theory | viva notes below |

### Important honesty notes

This project does **not** synchronize physical clocks. The global-state screen is not a consistent Chandy-Lamport snapshot because nodes may change while the client is collecting responses. The election is a simplified Bully-style availability check, not the full exchange of `OK` and `COORDINATOR` messages. The mutex is a Java monitor demonstration inside one JVM, not Ricart-Agrawala or a network distributed mutex.

## Unit III self-study notes

### Lodha and Kshemkalyani fair mutual exclusion

It is a distributed mutual-exclusion approach designed to give requesting processes fair service. Processes exchange request information and a process enters only when its request has suitable priority and it has the required permissions. It aims to avoid starvation: a continuously requesting low-ID process should not be bypassed forever. It is useful when several distributed processes need safe access to one shared resource. Important ideas are ordering requests, permission/deferred replies, fairness, and no central lock manager.

Viva: **Why fair?** It prevents a request from waiting forever while others repeatedly enter. **Is the project implementation this algorithm?** No; this project only demonstrates a local Java critical-section lock.

### Knapp's deadlock-detection classification

Knapp classifies distributed deadlock detection by how much information is collected and where decisions are made. Common categories are centralized (one site collects dependency information), hierarchical (local controllers report upward), and fully distributed (sites cooperate without one controller). A detector searches a wait-for graph for a cycle. The classification helps compare communication cost, speed, scalability, and false/obsolete information.

Viva: **What does a cycle in a wait-for graph mean?** It indicates deadlock. **Why is distributed detection harder?** Each site sees only part of the system and messages can be delayed.

## Unit IV study sheet

| Topic | Simple meaning, example, strengths and limitations |
|---|---|
| Distributed object-based systems | Objects on different machines call methods as if remote; Java RMI is an example. It improves modularity, but remote calls can fail or be slow. |
| Distributed web-based systems | Web clients and services cooperate through HTTP/APIs; an online shopping site is an example. They scale well but need load balancing and security. |
| Distributed file systems | Files are stored across machines but presented as one file system; HDFS is an example. They provide sharing and fault tolerance, with network delay and consistency trade-offs. |
| Serverless architecture | Cloud provider runs short functions on demand; AWS Lambda is an example. It reduces server management and scales automatically, but has cold starts and vendor dependence. |
| Megaport | A software-defined private connectivity provider between clouds and data centres. It gives flexible, private cloud links; it depends on provider locations and pricing. |
| Cloudflare | Global edge network for CDN, DNS, security and serverless functions. It reduces latency and DDoS risk; configuration errors can affect many users. |
| AWS | Large cloud platform offering compute, storage, databases and networking. Pay-as-you-go and broad services are advantages; cost control and lock-in are concerns. |
| Apache Hadoop | Big-data system using HDFS storage and MapReduce processing. It handles very large batch jobs; it is not ideal for low-latency interactive work. |
| Kubernetes | Platform that schedules and manages containers; a deployment runs desired replicas. It improves portability and recovery but has a learning and operations cost. |
| Blockchain / DLT | Replicated append-only ledger agreed by a network; Bitcoin is an example. It improves auditability and removes one central owner, but can be slow and energy/cost intensive. |
| Modern database trade-offs | Systems choose among consistency, availability, latency, cost and partition tolerance. A bank may favour strong consistency; a social feed may favour availability and low latency. |

Quick Unit IV viva questions: **What is HDFS?** A distributed file system used by Hadoop. **What does Kubernetes manage?** Containerized applications and their desired state. **What is serverless?** Running code on demand without managing servers. **Why use a CDN?** To serve content from a nearby edge location. **What is a database trade-off?** Improving consistency or durability can increase latency or cost.

## 20 short viva questions and answers

1. **What is a distributed system?** Independent computers that communicate to appear as one system.
2. **Why use TCP here?** It provides reliable ordered delivery for the demo messages.
3. **What is a Lamport clock?** A single logical counter that orders causally related events.
4. **What rule is used on receipt in Lamport clocks?** Set local time to `max(local, received) + 1`.
5. **What cannot Lamport timestamps tell?** Whether two events were concurrent.
6. **What is a vector clock?** One logical-counter entry per node.
7. **How is a vector received?** Take component-wise maximum, then increment the receiver's own entry.
8. **What does `[2,1,0]` mean?** The node knows two events from node 1 and one from node 2.
9. **Why keep both clocks?** Lamport is simple ordering; vectors give more causal information.
10. **What is global state here?** The collected current state of all three nodes.
11. **Is it a Chandy-Lamport snapshot?** No, it is a simple academic state collection.
12. **What is a heartbeat?** A small periodic/alive message used to detect reachability.
13. **What does UNAVAILABLE mean here?** The TCP connection to that configured port failed.
14. **What is Bully election?** A higher active node ID wins leadership.
15. **Who leads when all nodes are up?** Node 3.
16. **Where are courier events saved?** `data/events.txt`.
17. **What does mutual exclusion protect?** A critical section or shared resource.
18. **What does the project's mutex demonstrate?** Only one Java thread enters the guarded section at once.
19. **What happens if Delhi is stopped?** Beacon shows it unavailable and the simplified election can choose Mumbai.
20. **What is the main limitation?** It is a teaching demo without persistence, retries, authentication, or production failure recovery.

## Advantages, limitations, and future work

Advantages: small, no external libraries, clear terminal output, real TCP communication, and direct mapping to Unit III. Limitations: courier data is memory-only, client transfer is a request to the destination rather than a physical node-to-node handoff, state collection is not atomic, and failure detection is connection-based. Future work could add persistent storage, actual node-to-node transfer acknowledgements, timeouts/retries, authentication, and a true distributed snapshot/election protocol.

## Final structure

```text
DistributedCourierSystem/
  src/courier/
    BeaconProtocol.java       VectorClock.java
    ClientHandler.java        Courier.java       CourierClient.java
    CourierEvent.java         EventLogger.java   LamportClock.java
    LeaderElection.java       Main.java          Message.java
    MessageSender.java        MessageType.java   MutualExclusion.java
    Node.java                 NodeConfig.java
    clock/                    beacon/            election/
    mutex/                    unit4/
  data/events.txt
  docs/
    TESTING.md                SYLLABUS_MAPPING.md VIVA.md
    diagrams/                 unit3/             unit4/
  run_compile.bat             run_pune.bat       run_mumbai.bat
  run_delhi.bat               run_client.bat
  out/
  README.md
```

The `docs` folder contains separate Unit III and Unit IV study notes, Mermaid presentation diagrams, a complete test plan, honest syllabus mapping, and 45 short viva questions.
