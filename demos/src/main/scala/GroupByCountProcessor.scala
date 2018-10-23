import helpers.Utils
import org.apache.kafka.streams.kstream.Produced

object GroupByCountProcessor extends App {

  import org.apache.kafka.streams.scala._
  import Serdes._
  import ImplicitConversions._

  val builder = new StreamsBuilder
  builder.stream[String, String]("input-count")
    .groupBy { (_: String, value: String) =>
      value.split(",")(0)
    }
    .count
    .toStream
    .to("counts")

  val appId = this.getClass.getSimpleName.replace("$", "")
  import org.apache.kafka.streams.KafkaStreams
  val ks = new KafkaStreams(builder.build, Utils.getStreamsProperties(appId))
  ks.start
}
