# Apache Kafka™ and Kafka Streams Workshops

The repository contains the materials (i.e. [agendas](slides/#table-of-contents), [slides](slides/#apache-kafka-and-kafka-streams-workshops), [demos](demos), [exercises](exercises)) for [Apache Kafka™](https://kafka.apache.org/) and [Kafka Streams](https://kafka.apache.org/documentation/streams/) workshops led by [Jacek Laskowski](https://twitter.com/jaceklaskowski).

The current version of the workshop may take up to 3 days and is intended for a variety of IT roles (e.g. software developers, administrators, operators and architects).

The course is designed to help you master the essential aspects and operation of Apache Kafka and Kafka Streams in a fairly short time. After the course, you should have a clear understanding of Kafka with regard to administration, operation and application development.

No prior experience with Apache Kafka™ or Kafka Streams required.

## Topic 1: Apache Kafka™ for Administrators and Operators

1. [The Essentials of Apache Kafka](https://jaceklaskowski.github.io/kafka-workshop/slides/apache-kafka-essentials.html) (Architecture and Core Concepts)
2. Working with Kafka Topics
    1. Replicas and In-Sync Replicas (ISRs)
    2. Managing Topics with `kafka-topics` shell script
1. [Kafka Consumers, Consumer Groups, and Partition Rebalancing](https://jaceklaskowski.github.io/kafka-workshop/slides/kafka-consumers-consumer-groups-and-partition-rebalancing.html)
3. Kafka Configuration
    1. Kafka Server and Scheduled Recurring Tasks
    2. [AdminClient](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/admin/AdminClient.html)
4. [Monitoring Kafka Cluster](https://jaceklaskowski.github.io/kafka-workshop/slides/Monitoring-Kafka-Cluster.html)
    1. KafkaMetricsGroup
5. Troubleshooting and Debugging
    1. LogManager and log.dirs (/tmp/kafka-logs) - Failure and Recovery
6. Internals of KafkaServer and Other Services
1. [Kafka Security](https://jaceklaskowski.github.io/kafka-workshop/slides/kafka-security.html)

## Topic 2: Apache Kafka for Developers

1. Kafka Producers and Consumers
2. Developing Kafka Producers
    1. [Producer API](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/Producer.html)
    2. [KafkaProducer](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)
3. Developing Kafka Consumers
    1. [Consumer API](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/Consumer.html)
    2. [KafkaConsumer](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html), [TopicPartition](https://kafka.apache.org/20/javadoc/org/apache/kafka/common/TopicPartition.html), [ConsumerRecord](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html)

## Topic 3: Apache Kafka for Developers (Advanced Concepts)

- [ConsumerRebalanceListener](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerRebalanceListener.html)

## Topic 4: Kafka Streams (Scala API)

- Introduction to Kafka Streams (Core Concepts)
  - StreamsBuilder -- High-Level Stream Processing DSL
  - Topology -- Processor API
  - StreamsConfig and Important Configuration Properties
- Kafka Streams API
  - KStream, KTable, et al.
  - Aggregation
  - Join
- Stateful Stream Processing in Kafka Streams
  - StateStore
- Testing Kafka Streams Applications
- Troubleshooting and Debugging
