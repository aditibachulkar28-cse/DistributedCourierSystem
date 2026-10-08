# Distributed Database Trade-offs

Distributed databases balance consistency, availability, partition tolerance, latency, cost and scalability. The CAP theorem says that during a network partition a system cannot guarantee both full consistency and full availability.

A bank transfer normally prefers strong consistency so balances are correct. A social-media feed may accept temporarily stale data to remain available and fast. Replication increases fault tolerance and read capacity but adds coordination work and possible write delay.

Viva: **What is a partition?** A communication failure separating groups of nodes. **What is eventual consistency?** Replicas may differ temporarily but converge later.
