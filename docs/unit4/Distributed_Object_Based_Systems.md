# Distributed Object-Based Systems

A distributed object system lets an object on one machine request methods of an object on another machine. A client uses a proxy/stub, middleware sends the request, the remote object performs the method, and a result returns. Java RMI is a familiar example.

Advantages are modular design and object-oriented interfaces. Limitations are network delay, partial failure, serialization and security. They are distributed systems because objects and calls are spread across machines.

Viva: **What is a remote method call?** A method invocation sent across a network. **Why can it fail?** The remote machine or network can fail.
