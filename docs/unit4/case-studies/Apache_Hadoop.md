# Apache Hadoop Case Study

Hadoop stores large files in HDFS and processes data in parallel. The NameNode manages file metadata; DataNodes store replicated blocks. MapReduce moves computation close to the data and combines partial results.

Replication tolerates DataNode failure. Hadoop is useful for large batch processing, but it is not designed for low-latency request-response work. Viva: **What stores blocks?** DataNodes. **What holds metadata?** The NameNode.
