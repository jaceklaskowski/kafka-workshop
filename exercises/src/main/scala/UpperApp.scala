import java.util

import helpers.Utils
import org.apache.kafka.common.serialization.Serdes.StringSerde
import org.apache.kafka.streams.{KafkaStreams, KeyValue}
import org.apache.kafka.streams.kstream.{KStream, KeyValueMapper}

object UpperApp extends App {

  import org.apache.kafka.streams.scala._
  import Serdes._
  import ImplicitConversions._

  val builder = new StreamsBuilder
  builder
    .stream[String, String]("input")
    .flatMap[String, String] { (key: String, value: String) =>
      val Array(n, msg) = value.split(",")
      import collection.JavaConverters._
      n.toInt match {
          case 0 => Iterable.empty
          case 1 => Iterable((key, msg))
          case n => Iterable.fill(n)((key, msg))
        }
      }
    .to("output")

  val appId = this.getClass.getSimpleName.replace("$", "")
  val props = Utils.getStreamsProperties(appId)
  val ks = new KafkaStreams(builder.build, props)
  ks.start
}
