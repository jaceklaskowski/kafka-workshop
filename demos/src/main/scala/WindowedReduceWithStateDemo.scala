import java.util.concurrent.TimeUnit

import helpers.KafkaStreamsApp
import org.apache.kafka.streams.kstream.{Produced, TimeWindows}

object WindowedReduceWithStateDemo extends KafkaStreamsApp {

  import org.apache.kafka.streams.scala._
  import ImplicitConversions._
  import Serdes._

  val inputTopic = "windowed-reduce-input"
  val outputTopic = "windowed-reduce-output"
  implicit val produced = Produced.`with`(timeWindowedSerde[String], Serdes.String)
  builder.stream[String, String](inputTopic)
      .groupBy((_, value) => value)
      .windowedBy(TimeWindows.of(TimeUnit.SECONDS.toMillis(5)))
      .reduce((v1, v2) => v1 + v2)
      .toStream
      .to(outputTopic)

  ks.start()
}
