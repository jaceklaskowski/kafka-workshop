# Exercise 03: Your First Kafka Producer

Duration: **30 mins**

## Procedure

1. Read the javadoc of
   [KafkaProducer](https://kafka.apache.org/43/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)
   and [ProducerConfig](https://kafka.apache.org/43/javadoc/org/apache/kafka/clients/producer/ProducerConfig.html)

2. Create a new Scala/sbt project in IntelliJ IDEA: **kafka-101**
   * Leave the sbt/Scala version defaults — latest is fine

3. Add the `kafka-clients` dependency to `build.sbt`, matching the broker
   version from [01_kafka_docker.md](01_kafka_docker.md)

    ```scala
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "4.3.1"
    ```

   Use [mvnrepository](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients/) to know the proper entry for `kafka-clients` dependency.

4. Write the code of a Kafka producer **OrderProducerApp** that:
   1. Lives in **kafka101** package
   2. Creates a `KafkaProducer[String, String]`
   3. Starts with an empty `Properties` object and fill out the missing properties guided by exceptions at runtime
   4. Eventually, you should have a `Properties` object with `bootstrap.servers`,
    `key.serializer` and `value.serializer` — use the `ProducerConfig`
    constants, not raw string literals
   5. Sends 10 `ProducerRecord`s to topic **orders**, with keys
    `order-0` .. `order-9` and a value of your choosing
   6. Calls `close()` on the producer at the end so buffered records are
    flushed before the JVM exits

5. Run it, then verify the records arrived with the console consumer from
   [02_topics_and_cli.md](02_topics_and_cli.md) (recreate the **orders**
   topic first if you deleted it there)

6. Silence the slf4j "no logger attached" warnings
    1. Add [slf4j-api](https://mvnrepository.com/artifact/org.slf4j/slf4j-api) and a binding (e.g. [slf4j-simple](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple)) to `build.sbt`
    2. Drop a minimal config file in `src/main/resources` for whichever
       binding you picked

7. **Discuss**: what happens if you comment out `close()` (or replace it
   with `System.exit(0)` right after `send`)? Try it and watch the console
   consumer — do all 10 records still arrive?

## Complete Code

### OrderProducerApp

<details>
<summary>Complete producer code (spoiler — try it yourself first)</summary>

```scala
package kafka101

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object OrderProducerApp {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

    val producer = new KafkaProducer[String, String](props)

    try {
      for (i <- 0 until 10) {
        val key = s"order-$i"
        val value = s"""{"orderId":$i,"item":"widget","qty":${i + 1}}"""
        producer.send(new ProducerRecord[String, String]("orders", key, value))
      }
    } finally {
      producer.close()
    }
  }
}
```
</details>

### build.sbt

<details>
<summary>Complete build definition (spoiler — try it yourself first)</summary>

```scala
scalaVersion := "3.8.4"

name := "kafka-101"
libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "4.3.1",
  "org.slf4j" % "slf4j-api" % "2.0.18",
  "org.slf4j" % "slf4j-simple" % "2.0.18"
)
```
</details>
