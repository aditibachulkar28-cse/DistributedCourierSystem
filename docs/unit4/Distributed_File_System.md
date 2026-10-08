# Distributed File System

A distributed file system (DFS) stores files over multiple machines while presenting a unified file view. Files are split or distributed, replicated for availability, and recovered from failures. HDFS is a well-known example: a NameNode stores metadata and DataNodes store blocks.

Replication improves fault tolerance and read availability but uses extra storage and needs consistency management. A DFS is useful for large data shared by many machines.

Viva: **Why replicate blocks?** So data remains available after a node failure. **Is the supplied simulation HDFS?** No, it is an educational print-based simulation.
