# Apache Kafka™ and Kafka Streams Workshop

The repository contains the materials (i.e. agenda, [slides](slides), [exercises](exercises)) for [Apache Kafka™](https://kafka.apache.org/) and [Kafka Streams](https://kafka.apache.org/documentation/streams/) workshops led by Jacek Laskowski.

The current version of the workshop may take up to 3 days and is intended for a variety of IT roles (e.g. software developers, administrators, operators and architects).

The course is designed to help you master the essential aspects and operation of Apache Kafka and Kafka Streams in a fairly short time. After the course, you should have a clear understanding of Kafka with regard to administration, operation and application development.

No prior experience with Apache Kafka or Kafka Streams required.

## Topic 1: Apache Kafka for Administrators

- Architecture of Apache Kafka (Essentials, Core Concepts)
  - Kafka As Distributed Log
  - Brokers, Topics, Producers, Consumers, Partitions, Replicas, ISR
- Exercise: Installing Apache Kafka
- Exercise: Setting Up Multi-Broker Kafka Cluster
- Exercise: Using Kafka with Docker
- Working with Topics
- Kafka Configuration
  - Kafka Server and Scheduled Recurring Tasks
- Monitoring, Troubleshooting and Debugging
  - KafkaMetricsGroup
  - LogManager and /tmp/kafka-logs (Failure and Recovery)
- Exercise: Using jconsole for monitoring
- Internals of KafkaServer and Other Services

## Topic 2: Apache Kafka for Developers

- Developing Kafka Applications
- Kafka Producers and Consumers
- [Consumer API](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/Consumer.html)
  - [KafkaConsumer](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html), [TopicPartition](https://kafka.apache.org/20/javadoc/org/apache/kafka/common/TopicPartition.html), [ConsumerRecord](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html)
- [Producer API](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/Producer.html)
  - [KafkaProducer](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)

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
