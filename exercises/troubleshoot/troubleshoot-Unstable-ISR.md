# Trouble: Unstable ISR  
  
## Procedure  
  
 1. Create a Kafka cluster as you did in [kafka-exercise-Using-Kafka-with-Docker.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Using-Kafka-with-Docker.md "kafka-exercise-Using-Kafka-with-Docker.md")
 2. Create a producer as you did in [kafka-exercise-Developing-Kafka-Producer.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Developing-Kafka-Producer.md "kafka-exercise-Developing-Kafka-Producer.md")
 3.  Re-produce the below logs taken from **server.log** file:
>...
>[`timestamp`] INFO [Partition `your_topic`-`your_partition` broker=`brokerId#1`] Expanding ISR from `brokerId#1` to `brokerId#1,brokerId#2` (kafka.cluster.Partition)
[`timestamp`] INFO [Partition `your_topic`-`your_partition` broker=`brokerId#1`] Shrinking ISR from `brokerId#1,brokerId#2` to `brokerId#1`. Leader: (highWatermark: 5985570, endOffset: 5985849). Out of sync replicas: (brokerId: `brokerId#2`, endOffset: 5985570).
[timestamp] INFO [Partition `your_topic`-`your_partition` broker=`brokerId#1`] Expanding ISR from `brokerId#1` to `brokerId#1,brokerId#2` (kafka.cluster.Partition)
[`timestamp`] INFO [Partition `your_topic`-`your_partition`broker=`brokerId#1`] Shrinking ISR from `brokerId#1,brokerId#2` to `brokerId#1`. Leader: (highWatermark: 5991565, endOffset: 5991900). Out of sync replicas: (brokerId: `brokerId#2`, endOffset: 5991565)
>...

## Further Reading and Learning
 1. Replication
 2. ISR
