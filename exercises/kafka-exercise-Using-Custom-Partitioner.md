# Exercise: Using Custom Partitioner

Write a new Kafka application `PartitionerDemo` (using Kafka Producer API) as follows:

1. Write a custom `Partitioner`
    1. Implement [Partitioner](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/Partitioner.html) interface
    2. Review [Cluster](https://kafka.apache.org/20/javadoc/org/apache/kafka/common/Cluster.html) (which is one of the input arguments of `Partitioner.partition` method)
    3. Decide what to do when requested for a partition ID in `partition` method
2. Write a KafkaProducer
    1. Register the custom `Partitioner` using [ProducerConfig.PARTITIONER_CLASS_CONFIG](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/ProducerConfig.html#PARTITIONER_CLASS_CONFIG) property
    2. Use a `Callback` input object (to `Producer.send`) and display the partition ID
3. Create a topic with 2 partitions (on a single Kafka broker)
    1. Hint: Use as many `kafka-console-consumer` as many partitions are in use
    2. Use `kafka-console-consumer` with `--property print.key=true` to print keys
4. Execute the application and the following tests. Observe the behaviour.
    1. Define the partition key in `Producer.send`
    2. Don't specify the partition key
5. (EXTRA) Use `kafka-topics --alter` to increase the number of partitions and observe how `Cluster` reflects the change
    1. `./bin/kafka-topics.sh --zookeeper :2181 --alter --topic PartitionerDemo-input --partitions 3`

Duration: 30 mins
