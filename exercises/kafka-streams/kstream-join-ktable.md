# Exercise: Joining KStream with KTable ("Currency Exchange")

Develop a Kafka Streams application that simulate a Currency Exchange.

1. Use the high-level [Streams DSL](http://kafka.apache.org/27/documentation/streams/developer-guide/dsl-api.html)
1. Use [StreamsBuilder.stream](http://kafka.apache.org/27/javadoc/org/apache/kafka/streams/StreamsBuilder.html) to create a [KStream](http://kafka.apache.org/27/javadoc/org/apache/kafka/streams/kstream/KStream.html) for amounts to convert
1. Use [StreamsBuilder.table](http://kafka.apache.org/27/javadoc/org/apache/kafka/streams/StreamsBuilder.html) to create a [KTable](http://kafka.apache.org/27/javadoc/org/apache/kafka/streams/kstream/KTable.html) for rates
1. Use [KStream.join](http://kafka.apache.org/27/javadoc/org/apache/kafka/streams/kstream/KStream.html) to join amounts with rates

Module: **Kafka Streams**

Duration: **30 mins**

## Testing Commands

The following are some commands you can use to test your Kafka Streams application.

```text
kafkacat -P -b :9092 -t amounts -K :
```

```text
kafkacat -P -b :9092 -t rates -K :
```

```text
./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic out
```

```text
./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic rates --from-beginning
```

## Solution

```java
StreamsBuilder builder = new StreamsBuilder();

KStream<String, String> amounts = builder.stream("amounts");
KTable<String, String> rates = builder.table("rates");

KStream<String, String> out = amounts.join(rates, (amt, rate) -> "amt is " + amt * rate);
out.to("out");
```
