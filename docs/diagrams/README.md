# Presentation Diagrams

## 1. Courier system
```mermaid
flowchart LR
C[Courier Client] --> P[Pune : 5001]
C --> M[Mumbai : 5002]
C --> D[Delhi : 5003]
P --> E[events.txt]
M --> E
D --> E
```
## 2. Lamport clock
```mermaid
sequenceDiagram
Pune->>Mumbai: timestamp 4
Mumbai->>Mumbai: max(local,4)+1
```
## 3. Vector clock
```mermaid
flowchart LR
N1[Node 1: 1,0,0] --> N2[Node 2: 1,1,0] --> N3[Node 3: 1,1,1]
```
## 4. Leader election
```mermaid
flowchart LR
N1 --> N2 --> N3[Highest alive = leader]
```
## 5. Mutual exclusion
```mermaid
sequenceDiagram
N1->>CS: enter
N2->>CS: wait
N1->>CS: release
CS->>N2: enter
```
## 6. Beacon protocol
```mermaid
flowchart LR
B[Beacon] --> N1[Node 1 ALIVE]
B --> N2[Node 2 ALIVE]
B --> N3[Node 3 UNAVAILABLE if stopped]
```
## 7. Global state
```mermaid
flowchart LR
C[Client] --> P[Pune state]
C --> M[Mumbai state]
C --> D[Delhi state]
```
## 8. DFS
```mermaid
flowchart LR
F[File A] --> N1[Node 1]
F --> N3[Replica on Node 3]
```
## 9. Serverless
```mermaid
flowchart LR
Event --> Function --> Result
```
## 10. Hadoop
```mermaid
flowchart TD
NN[NameNode metadata] --> DN1[DataNode blocks]
NN --> DN2[DataNode replicas]
```
## 11. Kubernetes
```mermaid
flowchart TD
CP[Control Plane] --> W1[Worker Node / Pod]
CP --> W2[Worker Node / Pod]
```
## 12. Blockchain
```mermaid
flowchart LR
B1[Block 1] --> B2[Block 2] --> B3[Block 3]
```
