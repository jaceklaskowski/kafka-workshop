# Exercise: Sending Messages with Callback

## Procedure

1. Review the available `send` methods in [KafkaProducer](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)
2. Read the javadoc of [KafkaProducer.send​(ProducerRecord<K,V> record, Callback callback)](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html#send-org.apache.kafka.clients.producer.ProducerRecord-org.apache.kafka.clients.producer.Callback-)
    1. Explore [Callback](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/producer/Callback.html) interface
3. Answer the following questions with regards to the Callback interface:
    1. What does Callback give when you send a message to an existing topic?
    2. What happens when sending a message to an unexisting topic?
    3. Mind the automatic topic creation feature
4. Duration: 15 mins