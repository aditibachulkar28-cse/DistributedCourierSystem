# Knapp's Classification of Distributed Deadlock Detection

## Distributed deadlock

A deadlock happens when processes wait forever for resources held by one another. In a distributed system, each site sees only part of the wait-for graph, so detection needs communication.

## Classification

- **Centralized:** one site gathers dependency information and checks for cycles.
- **Hierarchical:** local controllers report to higher-level controllers.
- **Fully distributed:** sites cooperate without a single controller.

## Example

Node 1 waits for node 2, node 2 waits for node 3, and node 3 waits for node 1. This cycle is a deadlock.

## Advantages and limitations

Centralized detection is simple but has a bottleneck. Hierarchical detection scales better but adds levels. Fully distributed detection avoids a central failure point but is harder because information may be delayed or outdated.

## Viva

**Why is detection required?** To identify processes that will never progress.  
**What does a cycle mean?** A circular wait and therefore deadlock.  
**Is deadlock detection implemented in the courier workflow?** No; this is theory only.
