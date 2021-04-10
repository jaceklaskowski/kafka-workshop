# Exercise: Using Consumer Group

In this exercise you will create a **consumer group** and observe what happens when the number of Kafka consumers changes.

Duration: **30 mins**

**TIP**: Use [kafkacat](https://github.com/edenhill/kafkacat) for more control (than `kafka-console-producer` that [does not support sending messages to a given partition](https://stackoverflow.com/q/26553412/1305344)).

## Procedure

1. Create a topic **t1** with 3 partitions

    ```text
    ./bin/kafka-topics.sh --bootstrap-server :9092 --create --topic t1 --partitions 3
    ```

1. Start a new consumer **c1** in a consumer group **CG1**

    ```text
    ./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic t1 --consumer-property group.id=CG1
    ```

1. Start a Kafka producer that is attached to partition `0`. Send a couple of messages with the key `0` for easier identification what producer sends what messages.

    ```text
    kafkacat -P -b :9092 -t t1 -p 0 -K :
    ```

    (`-K` is for a key-value separator)

1. Start another Kafka producer to send messages to partition `2` (with `2` key)

    ```text
    kafkacat -P -b :9092 -t t1 -p 2 -K :
    ```

At this point you should have 3 partitions, 2 producers and 1 consumer. Observe what and how messages are consumed. Simply send messages so you can identity what message used what partition.

1. Start a new consumer **c2** in the CG1 consumer group

    ```text
    ./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic t1 --consumer-property group.id=CG1
    ```

1. Observe the logs in the Kafka broker

1. Send a couple of message to observe if and how messages are distributed across the two consumers

1. Start a new consumer **c3** in the CG1 consumer group

    ```text
    ./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic t1 --consumer-property group.id=CG1
    ```

1. Send a couple of message to observe if and how messages are distributed across the consumers

1. Shut down any of the running consumers and observe which consumer takes over the "abandoned" partition
