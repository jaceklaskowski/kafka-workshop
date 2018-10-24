# Apache Kafka™ and Kafka Streams Workshop

The repository contains the materials (i.e. agenda, [slides](slides#apache-kafka-and-kafka-streams-workshops), [demos](demos), [exercises](exercises)) for [Apache Kafka™](https://kafka.apache.org/) and [Kafka Streams](https://kafka.apache.org/documentation/streams/) workshops led by Jacek Laskowski.

The current version of the workshop may take up to 3 days and is intended for a variety of IT roles (e.g. software developers, administrators, operators and architects).

The course is designed to help you master the essential aspects and operation of Apache Kafka and Kafka Streams in a fairly short time. After the course, you should have a clear understanding of Kafka with regard to administration, operation and application development.

No prior experience with Apache Kafka or Kafka Streams required.

## Topic 1: Apache Kafka for Administrators

1. [The Essentials of Apache Kafka](http://blog.jaceklaskowski.pl/kafka-workshop/slides/apache-kafka-essentials.html) (Architecture and Core Concepts)
    1. [Exercise: Installing and Running Kafka Broker](exercises/kafka-exercise-Installing-and-Running-Kafka-Broker.md)
    2. [Exercise: Configuring Multi-Broker Kafka Cluster](exercises/kafka-exercise-Configuring-Multi-Broker-Kafka-Cluster.md)
    3. [Exercise: Using Kafka with Docker](exercises/kafka-exercise-Using-Kafka-with-Docker.md)
2. Working with Topics
    1. Replicas and In-Sync Replicas (ISRs)
    2. Managing Topics with `kafka-topics` shell script
3. Kafka Configuration
    1. Kafka Server and Scheduled Recurring Tasks
4. Monitoring, Troubleshooting and Debugging
    1. KafkaMetricsGroup
    2. LogManager and /tmp/kafka-logs (Failure and Recovery)
    3. Exercise: Using jconsole for monitoring
5. Internals of KafkaServer and Other Services

## Topic 2: Apache Kafka for Developers

- Kafka Producers and Consumers
- Developing Kafka Applications Using Producer API
  - [Producer API](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/Producer.html)
  - [KafkaProducer](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)
  - [Exercise: Developing Kafka Producer](exercises/kafka-exercise-Developing-Kafka-Producer.md)
  - [Exercise: Sending Messages with Callback](exercises/kafka-exercise-Sending-Messages-with-Callback.md)
- [Consumer API](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/Consumer.html)
  - [KafkaConsumer](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html), [TopicPartition](https://kafka.apache.org/20/javadoc/org/apache/kafka/common/TopicPartition.html), [ConsumerRecord](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html)

## Topic 3: Apache Kafka for Developers (Advanced Concepts)

- [ConsumerRebalanceListener](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerRebalanceListener.html)
- [AdminClient](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/admin/AdminClient.html)

## Topic 4: Kafka Streams (Scala API)

- Introduction to Kafka Streams (Core Concepts)
  - StreamsBuilder -- High-Level Stream Processing DSL
  - Topology -- Processor API
  - StreamsConfig and Important Configuration Properties
- Exercise: Developing Your First Kafka Streams Application (Scala)
  - Scala API for Kafka Streams
  - Using Kafka for Streaming
- Kafka Streams API
  - KStream, KTable, et al.
  - Aggregation
  - Join
- Stateful Stream Processing in Kafka Streams
  - StateStore
- Testing Kafka Streams Applications
- Troubleshooting and Debugging
