# Exercise: Configuring Multi-Broker Kafka Cluster

## Procedure

1. Review `config/zookeeper.properties` configuration file
2. Start Zookeeper
    1. Use `zookeeper-server-start.sh` as usual
3. Review `config/server.properties` configuration file
4. Start a Kafka server with `broker.id=0` (the default configuration)
    1. Use `kafka-server-start.sh` as usual
5. Start another Kafka server with `broker.id=1`
    1. Create a separate **server.properties** configuration file, e.g. `server-1.properties`
    2. You will have to change at least two more configuration properties before the server gets up and running
6. Start another Kafka server with `broker.id=2`
    1. Use **--override** command-line option (of `kafka-server-start.sh`) to override configuration properties

## Further Reading and Learning

1. [Kafka Tools](https://jaceklaskowski.gitbooks.io/apache-kafka/content/kafka-tools.html) (Mastering Apache Kafka)
