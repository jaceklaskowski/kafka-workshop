# Exercise: Developing Kafka Consumer

1. Read the javadoc of [KafkaConsumer](http://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html) to know how to use the Consumer API (to consume messages from Kafka)
2. Create a new Scala/sbt project in IntelliJ IDEA
3. Define dependency for Kafka Clients API library
    1. Use [mvnrepository](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients/2.0.0) to know the proper entry for `kafka-clients` dependency
4. Write a Kafka consumer
    1. Name of the object: **KafkaConsumerApp**
    2. Start with an empty `Properties` object and fill out the missing properties per exceptions at runtime
    3. Use [ConsumerConfig](https://kafka.apache.org/20/javadoc/org/apache/kafka/clients/consumer/ConsumerConfig.html) constants (not string values for properties)
    4. Don't forget to `close` the consumer (so the messages are actually acknowledged to the broker)
5. Run the Kafka consumer
    1. Use `kafka-console-producer` to produce messages
6. Fix the slf4j logging errors
    1. Define dependencies for `slf4j-api` and `slf4j-log4j12` in `build.sbt`
    2. Create `src/main/resources/log4j.properties` as the configuration file
    3. Use Apache Kafka's `config/log4j.properties` as the sample (4 first non-comment lines)

Duration: 30 mins