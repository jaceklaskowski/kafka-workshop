# Exercise: Using Consumer Group

In this exercise you will create a **consumer group** and observe what happens when the number of members changes.

Duration: **30 mins**

## Procedure

1. (optional) Install [kafkacat - Apache Kafka producer and consumer tool](https://github.com/edenhill/kafkacat)
  * `kafka-console-producer` [does not support sending messages to a given partition](https://stackoverflow.com/q/26553412/1305344))
1. Create a topic **t1** with 3 partitions
1. Start a new consumer **c1** in a consumer group **CG1**
1. Send messages to partition 0 and use 0 for the key
1. Send messages to partition 2 and use 2 for the key

At this point you should have 3 partitions, 2 producers and 1 consumer. Observe what and how messages are consumed. Simply send messages so you can identity what message used what partition.

1. Start a new consumer **c2** in the CG1 consumer group
1. Observe what messages are consumed by the consumers
1. Start a new consumer **c3** in the CG1 consumer group
1. Observe what messages are consumed by the consumers
1. Shut down any of the running consumers
1. Observe which consumer takes over the partition
