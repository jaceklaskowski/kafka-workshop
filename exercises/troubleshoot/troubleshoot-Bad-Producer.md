
# Trouble: Bad Producer
  
## Procedure  
  
 1. Create a Kafka cluster as you did in [kafka-exercise-Using-Kafka-with-Docker.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Using-Kafka-with-Docker.md "kafka-exercise-Using-Kafka-with-Docker.md")
 2. Create a producer as you did in [kafka-exercise-Developing-Kafka-Producer.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Developing-Kafka-Producer.md "kafka-exercise-Developing-Kafka-Producer.md")
 3. Make your producer use transactions (how many brokers does your cluster has to have?)
 4.  Re-produce the below logs taken from **server.log** file:
>...
>[`timestamp`] ERROR [ReplicaManager broker=1001] Error processing append operation on partition test-0 (kafka.server.ReplicaManager)
org.apache.kafka.common.errors.ProducerFencedException: Producer's epoch at offset 40246 is no longer valid in partition test-0: 11 (request epoch), 13 (current epoch)
>...

## Further Reading and Learning
 1. Kafka Transactions
 2. Zombie Fencing
