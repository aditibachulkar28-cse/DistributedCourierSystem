# Lodha and Kshemkalyani Fair Mutual Exclusion

## Definition and purpose

This is a distributed mutual-exclusion algorithm designed to let processes access one critical section fairly. Its purpose is to prevent two processes from using a shared resource simultaneously while also avoiding starvation.

## Basic working

Each process announces a request, compares request priority, and exchanges permissions or defers replies when another request has priority. A process enters only after it has the needed permissions. Fairness means an older eligible request is not repeatedly overtaken by newer requests.

## Example

If node 1 requests first and node 2 requests later, node 2 should not continually enter before node 1. Node 2 waits or defers its permission according to the request order.

## Advantages and limitations

- Fairer than a simple local lock; helps prevent starvation.
- Does not need one permanent central coordinator.
- Requires message exchange and correct handling of deferred replies.
- Network failures and delayed messages make a real implementation more difficult.

## Relation to this project

`MutualExclusion.java` is only a Java `synchronized`/`wait`/`notifyAll` demonstration in one JVM. It is not this distributed algorithm.

## Viva

**What is fairness?** Every valid requester eventually gets a turn.  
**Why defer a reply?** To let an earlier or higher-priority request enter first.  
**Is this project a Lodha-Kshemkalyani implementation?** No; it is theory only.
