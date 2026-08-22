# Exercise 03: Your First Scala Producer

Duration: **45 mins**

## Procedure

1. Read the javadoc of [KafkaProducer](https://kafka.apache.org/40/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)
   and [ProducerConfig](https://kafka.apache.org/40/javadoc/org/apache/kafka/clients/producer/ProducerConfig.html)

2. Create a new Scala/sbt project in IntelliJ IDEA (leave the sbt/Scala
   version defaults — latest is fine)

3. Add the `kafka-clients` dependency to `build.sbt`, matching the broker
   version from [01_kafka_docker.md](01_kafka_docker.md)

    ```scala
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "4.3.1"
    ```

4. Write an object **OrderProducerApp** that:
    1. Builds a `Properties` object with `bootstrap.servers`,
       `key.serializer` and `value.serializer` — use the `ProducerConfig`
       constants, not raw string literals
    2. Creates a `KafkaProducer[String, String]`
    3. Sends 10 `ProducerRecord`s to topic **orders**, with keys
       `order-0` .. `order-9` and a value of your choosing
    4. Calls `close()` on the producer at the end so buffered records are
       flushed before the JVM exits

5. Run it, then verify the records arrived with the console consumer from
   [02_topics_and_cli.md](02_topics_and_cli.md) (recreate the **orders**
   topic first if you deleted it there)

6. Silence the slf4j "no logger attached" warnings
    1. Add `slf4j-api` and a binding (e.g. `slf4j-simple` or
       `logback-classic`) to `build.sbt`
    2. Drop a minimal config file in `src/main/resources` for whichever
       binding you picked

7. **Discuss**: what happens if you comment out `close()` (or replace it
   with `System.exit(0)` right after `send`)? Try it and watch the console
   consumer — do all 10 records still arrive?
