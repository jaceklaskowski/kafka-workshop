# Exercise: Processing Record Stream with KStream

Develop a standalone Kafka Streams application (using IntelliJ IDEA) that reads records in from a source and writes it out to a sink.

1. Use the high-level [Streams DSL](http://kafka.apache.org/21/documentation/streams/developer-guide/dsl-api.html)
1. Use [StreamsBuilder.stream](http://kafka.apache.org/21/javadoc/org/apache/kafka/streams/StreamsBuilder.html) to create a [KStream](http://kafka.apache.org/21/javadoc/org/apache/kafka/streams/kstream/KStream.html) to read records in
1. Use [KStream.to](http://kafka.apache.org/21/javadoc/org/apache/kafka/streams/kstream/KStream.html) to write records out

In the end, use `sbt assembly` and `java -jar` to run the application.

Module: **Kafka Streams**

Duration: **30 mins**