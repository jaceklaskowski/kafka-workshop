# Exercise: Configuring Multi-Broker Kafka Cluster

In this exercise you will configure a Kafka cluster with 3 brokers.

Duration: **30 mins**

**TIP** Use [bit.ly/kafka-exercise-admin-03](https://bit.ly/kafka-exercise-admin-03) to access this exercise.

## Procedure

1. Review **config/zookeeper.properties** default configuration file of Zookeeper
1. Start a Zookeeper server
    * Use **zookeeper-server-start.sh** shell script
1. Use **zookeeper-shell.sh :2181 ls /brokers/ids** to make sure no Kafka brokers are available
1. Review **config/server.properties** default configuration file of a Kafka broker
1. Start a Kafka broker with the default configuration
    * Use **kafka-server-start.sh** shell script
1. Use **zookeeper-shell.sh :2181 ls /brokers/ids** to make sure that 1 broker is registered
1. Start another Kafka broker with **broker.id=10**
    * Create a separate **server.properties** configuration file, e.g. **server-10.properties**
    * Set other configuration properties to have the server up and running
1. Use **zookeeper-shell.sh :2181 ls /brokers/ids** to make sure that 2 brokers are registered
1. Start another Kafka broker with **broker.id=20**
    * Use **--override** command-line option (of **kafka-server-start.sh**) to override configuration properties
1. Use **zookeeper-shell.sh :2181 ls /brokers/ids** to make sure that 3 brokers are registered

After the above steps you should have broker `0` as the controller broker. Let's see controller election in action.

1. Remove **/controller** znode and observe the logs
2. After this znode gets recreated, it will contain the ID of the active controller broker

## Further Reading and Learning

1. [Kafka Tools](https://jaceklaskowski.gitbooks.io/apache-kafka/content/kafka-tools.html)
