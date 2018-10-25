# Exercise: Using kafka-topics with Multi-Broker Cluster

1. Review the available options of `kafka-topics` shell script
    1. Use `--list` to list available topics
2. Configure a 3-broker cluster
3. Create a topic with 3 partitions and replication factor of 2
    1. `./bin/kafka-topics.sh --zookeeper :2181 --create --topic events --partitions 3 --replication-factor 2`
4. Explore the configuration of the topic using `--describe` option
    1. `./bin/kafka-topics.sh --zookeeper :2181 --describe --topic [topic]`
    2. What are Leader, Replicas, Isr?
5. Write a Kafka producer to send a message to the partition 0 and execute it (to send a message)
6. Kill the leader (broker) of the partition 0
7. Review topic configuration using `--describe`
    1. A new leader should be elected (from available ISRs)
8. Kill the new leader of the partition 0
9. Execute the Kafka producer (that sends to the partition 0 with no leader broker)
    1. What happens?

Duration: 30 mins