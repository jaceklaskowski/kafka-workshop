# Exercise: Using kafka-topics with Multi-Broker Cluster

1. Configure a 3-broker cluster
2. Review the available options of `kafka-topics` shell script
    1. Use `--help` command-line option to print usage information
3. Create a topic with 3 partitions and replication factor of 2
    1. Use `--create` command-line option
    2. `./bin/kafka-topics.sh --zookeeper :2181 --create --topic [topic] --partitions 3 --replication-factor 2`
4. List all available topics
    1. Use `--list` command-line option
5. List details of a given topic
    1. Use `--describe` command-line option
    2. `./bin/kafka-topics.sh --zookeeper :2181 --describe --topic [topic]`
    3. What are Leader, Replicas, Isr?
6. Write a Kafka producer to send a message to the partition 0 and execute it (to send a message)
7. Kill the leader (broker) of the partition 0
8. Review topic configuration using `--describe`
    1. A new leader should be elected (from available ISRs)
9. Kill the new leader of the partition 0
10. Execute the Kafka producer (that sends to the partition 0 with no leader broker)
    1. What happens?

Duration: 30 mins