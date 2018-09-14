import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.{Produced, TimeWindows}

object WindowedByApp extends App {

  import org.apache.kafka.streams.scala._
  import ImplicitConversions._
  import Serdes._

  val builder = new StreamsBuilder
  implicit val produced = Produced.`with`(timeWindowedSerde[String], Serdes.Long)
  builder.stream[String, String]("windowed-input")
    .groupBy { (_, v) => v }
    .windowedBy(TimeWindows.of(TimeUnit.SECONDS.toMillis(1)))
    .count()
    .toStream
    .to("windowed-output")

  val appId = this.getClass.getSimpleName.replace("$", "")
  val ks = new KafkaStreams(builder.build, Utils.getStreamsProperties(appId))
  ks.start
}
