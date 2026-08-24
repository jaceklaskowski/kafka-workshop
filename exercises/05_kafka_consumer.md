# Exercise 05: Your First Kafka Consumer

Duration: **30 mins**

## Procedure

1. Read the javadoc of
   [KafkaConsumer](https://kafka.apache.org/43/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html)
   and [ConsumerConfig](https://kafka.apache.org/43/javadoc/org/apache/kafka/clients/consumer/ConsumerConfig.html)

2. In the same (or a new) sbt project as [03_scala_producer.md](03_scala_producer.md),
   write the code of a Kafka consumer **OrderConsumerApp** that:
   1. Lives in **kafka101** package
   2. Builds a `Properties` object with `bootstrap.servers`,
    `key.deserializer`, `value.deserializer`, and `group.id=order-consumer`
   3. Creates a `KafkaConsumer[String, String]` and `subscribe`s to topic **orders**
   4. Runs a `while (true)` loop calling
   `poll(Duration.ofMillis(500))`,
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

7. Optional, going past the basics:
   1. `enable.auto.commit=false` and add explicit `commitSync()` after each batch.
   2. `client.id` to make the consumer identifiable in `kafka-consumer-groups --describe`.

## Complete Code

### OrderConsumerApp

<details>
<summary>Complete consumer code (spoiler — try it yourself first)</summary>

```scala
package kafka101

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.errors.WakeupException
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.Properties

object OrderConsumerApp {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-consumer")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])

    val consumer = KafkaConsumer[String, String](props)

    val running = new java.util.concurrent.atomic.AtomicBoolean(true)
    sys.addShutdownHook {
      running.set(false); consumer.wakeup()
    }

    import scala.jdk.CollectionConverters._
    consumer.subscribe(Seq("orders").asJava)

    import scala.concurrent.duration._
    import scala.jdk.DurationConverters._
    try {
      while(running.get()) {
        consumer
          .poll(1.second.toJava)
          .asScala
          .foreach { r =>
            println(s"${r.topic()}-${r.partition()}@${r.offset()} ${r.key()} -> ${r.value()}")
          }
      }
    }
    catch case _: WakeupException => print("expected on shutdown")
    finally {
      consumer.close()
    }
  }
}
```
</details>
