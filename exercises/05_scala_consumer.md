# Exercise 05: Your First Scala Consumer

Duration: **45 mins**

## Procedure

1. Read the javadoc of [KafkaConsumer](https://kafka.apache.org/40/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html)
   and [ConsumerConfig](https://kafka.apache.org/40/javadoc/org/apache/kafka/clients/consumer/ConsumerConfig.html)

2. In the same (or a new) sbt project as [03_scala_producer.md](03_scala_producer.md),
   write an object **OrderConsumerApp** that:
    1. Builds a `Properties` object with `bootstrap.servers`,
       `key.deserializer`, `value.deserializer`, and `group.id=order-consumers`
    2. Creates a `KafkaConsumer[String, String]` and `subscribe`s to topic
       **orders**
    3. Runs a `while (true)` loop calling `poll(Duration.ofMillis(500))`,
       printing each `ConsumerRecord`'s key, value, partition and offset

3. Run **OrderProducerApp** from Exercise 03 in one terminal and
   **OrderConsumerApp** in another. Confirm every record is printed.

4. Stop the consumer (Ctrl+C) and restart it. **Predict** first whether it
   re-reads the records it already saw, then run it and check — explain
   the result in terms of `auto.offset.reset` and committed offsets (the
   default `group.id` here means it's a *new* run of the *same* group, so
   it resumes from the last committed offset, not from the beginning)

5. Change `auto.offset.reset` to `earliest`, pick a brand-new `group.id`,
   and re-run — confirm it now replays the whole topic from the start

6. **Discuss**: what's the difference between `auto.offset.reset` and
   `enable.auto.commit`? (One decides where a *new* group starts; the
   other decides whether/how often offsets are written back to Kafka.)
