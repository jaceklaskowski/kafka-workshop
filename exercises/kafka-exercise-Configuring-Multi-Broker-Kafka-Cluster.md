# Exercise: Configuring Multi-Broker Kafka Cluster

## Procedure

1. Start Zookeeper
2. Start a Kafka server with `broker.id=0` (the default configuration)
3. Start another Kafka server with `broker.id=1`
    1. Create a separate **server.properties** configuration file
4. Start another Kafka server with `broker.id=2`
    1. Use **--override** command-line option
5. Create Topic using **kafka-topics** shell script
    1. Topic name: **events**
    2. Number of partitions: **3**
    3. Number of replicas: **2**

## Further Reading and Learning

1. [Kafka Tools](https://jaceklaskowski.gitbooks.io/apache-kafka/content/kafka-tools.html) (Mastering Apache Kafka)
