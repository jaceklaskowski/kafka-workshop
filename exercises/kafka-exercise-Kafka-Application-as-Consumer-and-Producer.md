# Exercise: Kafka Application as Consumer and Producer

Write a Kafka application (using Kafka [Consumer](https://kafka.apache.org/21/javadoc/index.html?org/apache/kafka/clients/consumer/KafkaConsumer.html) and [Producer](https://kafka.apache.org/21/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html) APIs) that does the following:

1. Consumes messages from a topic, e.g. `input`
2. Sends the messages to another topic, e.g. `output`
    1. The names of the input and output topics may also be given on the command line
3. Apply a transformation on the value, e.g. "upper" the strings
4. Uses the value of the messages to decide what topic to send the output messages to
    1. A record with the value of `topic-1` would be sent to `topic-1` topic

Duration: **30 mins**

## Questions

1. What happens when you use the same topic as the input and the output?
2. What happens when you Ctrl+C?
