# Apache Kafka™ and Kafka Streams Workshop

The repository contains the materials (i.e. agenda, [slides](slides), [exercises](exercises)) for [Apache Kafka™](https://kafka.apache.org/) and [Kafka Streams](https://kafka.apache.org/documentation/streams/) workshops led by Jacek Laskowski.

The current version of the workshop may take up to 3 days and is intended for a variety of IT roles (e.g. software developers, administrators, operators and architects).

The goal is to get you fairly comfortable with Kafka and Kafka Streams in nearly no time. After the course, you should be well-versed with Kafka with regard to administration, operation and application development.

No prior experience with Apache Kafka or Kafka Streams required.

## Topic 1: Apache Kafka for Administrators

- Architecture of Apache Kafka (Core Concepts)
  - Kafka As Distributed Log
  - Topics, Partitions, Replicas, ISR
- Exercise: Installing Apache Kafka
- Exercise: Setting Up Multi-Broker Kafka Cluster
- Exercise: Using Kafka with Docker
- Kafka Configuration
  - Kafka Server and Scheduled Recurring Tasks
- Monitoring, Troubleshooting and Debugging
  - KafkaMetricsGroup
  - LogManager and /tmp/kafka-logs (Failure and Recovery)
- Exercise: Using jconsole for monitoring
- Internals of KafkaServer and Other Services

## Topic 2: Apache Kafka for Developers

- Developing Kafka Applications
- Consumer API
  - TopicPartition, ConsumerRecord, et al.
- Producer API

## Topic 3: Apache Kafka for Developers (Advanced Concepts)

- ConsumerRebalanceListener

## Topic 4: Kafka Streams (Scala API)

- Introduction to Kafka Streams (Core Concepts)
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
