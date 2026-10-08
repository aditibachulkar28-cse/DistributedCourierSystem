# Viva Questions and Short Answers

1. **What is a distributed system?** Independent computers cooperating through messages.
2. **Why use TCP?** It gives ordered, reliable byte delivery.
3. **What is a physical clock?** A clock based on real time.
4. **What is clock synchronization?** Reducing differences between clocks.
5. **What does the clock demo change?** Only simulated Java values, not OS clocks.
6. **What is a Lamport clock?** A logical counter for ordering events.
7. **Lamport receive rule?** `max(local, received)+1`.
8. **What is a local event?** An event occurring without receiving a message.
9. **What can Lamport clocks not identify?** Concurrency exactly.
10. **What is a vector clock?** One logical-counter entry per node.
11. **Vector receive rule?** Component-wise maximum, then increment receiver entry.
12. **What is happened-before?** An event that causally influences another.
13. **What are concurrent events?** Events with no causal ordering.
14. **What is global state?** Combined current state of participating nodes.
15. **Is this a Chandy-Lamport snapshot?** No, it is a simple state collection.
16. **What is Bully election?** Highest active node ID becomes leader.
17. **Who leads with all nodes active?** Node 3.
18. **What if Delhi is down?** Node 2 becomes leader in this simplified demo.
19. **What is mutual exclusion?** Allowing only one participant in a critical section.
20. **Is this mutex distributed?** No, it is a local Java monitor demonstration.
21. **What is a heartbeat?** A small message/check proving reachability.
22. **What does UNAVAILABLE mean here?** TCP connection to that node failed.
23. **Where are events logged?** `data/events.txt`.
24. **What is fairness in mutex?** A requester should not starve forever.
25. **What is Lodha-Kshemkalyani about?** Fair distributed mutual exclusion.
26. **What is a distributed deadlock?** A circular wait across processes/sites.
27. **What indicates deadlock in a wait-for graph?** A cycle.
28. **What is a distributed object?** An object whose method may be invoked remotely.
29. **What is a web-based distributed system?** Services cooperating through web protocols/APIs.
30. **Why use load balancing?** To spread work and improve availability.
31. **What is a distributed file system?** One file view backed by several machines.
32. **Why replicate files?** To survive a node failure and improve reads.
33. **What does serverless mean?** Provider-managed servers running functions on demand.
34. **What is AWS Lambda?** An event-driven serverless function service.
35. **What does a CDN do?** Serves content from nearby edge sites.
36. **What is Megaport?** Software-defined connectivity between distributed cloud/data-centre networks.
37. **What is HDFS?** Hadoop Distributed File System.
38. **What does Hadoop's NameNode do?** Stores file-system metadata.
39. **What does Kubernetes manage?** Containerized workloads across a cluster.
40. **What is a Pod?** Kubernetes' smallest deployable unit.
41. **What is blockchain?** A linked, replicated ledger of records.
42. **What is consensus?** Node agreement on valid updates.
43. **State CAP theorem.** During a partition, a system cannot guarantee both full consistency and availability.
44. **What is eventual consistency?** Replicas may differ temporarily but later converge.
45. **Main project limitation?** It is an educational TCP demo without durable storage or production failure recovery.
