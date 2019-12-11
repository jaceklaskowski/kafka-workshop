# Exercise: Partition Leader Election and Consumer Fail-Over

In this exercise you will experiment with **partition leader election** and **consumer fail-over**.

Duration: **30 mins**

**TIP** Use [bit.ly/kafka-exercise-admin-05](https://bit.ly/kafka-exercise-admin-05) to access this exercise.

## Procedure

1. Create a 3-broker Kafka cluster
  * Use custom **broker.id** to make sure the ids are yours in kafka-topics later
  * KafkaServer id=59
  * KafkaServer id=66
  * KafkaServer id=47
1. Create a topic **t1** with 1 partition and replication factor of 2
  * `./bin/kafka-topics.sh --create --bootstrap-server :9092 --topic t1 --partitions 1 --replication-factor 2`
  * `./bin/kafka-topics.sh --describe --bootstrap-server :9092 --topic t1`
1. Produce and consume a few messages
  * Use [kafkacat - Apache Kafka producer and consumer tool](https://github.com/edenhill/kafkacat)

At this point you should have a topic with replication factor of 2, one producer and one consumer. You made sure that communication between the clients is established (the clients can exchange messages).

1. Shut down the partition leader
1. Observe what happens with the clients and whether they can communicate with the cluster and with each other
1. Use kafka-topics to check out the leader
