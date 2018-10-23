import helpers.Utils
import org.apache.kafka.streams.kstream.{Produced, WindowedSerdes}


// Make sure you publish records with non-null keys!
// As they do not participate in counting
/**

./bin/kafka-console-producer.sh \
  --topic windowed-input \
  --broker-list localhost:9092 \
  --property parse.key=true \
  --property key.separator=,

  */
object WindowedCountDemo extends App {

  import org.apache.kafka.streams.scala._
  import ImplicitConversions._
  import Serdes._

  val inputTopic = "windowed-input"
  val storeName = "minute-windows"

  import org.apache.kafka.streams.kstream.TimeWindows
  import scala.concurrent.duration._
  val minute = 1.minutes.toMillis
  val oneMinuteTumblingWindow = TimeWindows.of(minute).until(minute)


  import org.apache.kafka.streams.kstream.Printed
  import org.apache.kafka.streams.kstream.Windowed
  val stdout = Printed
    .toSysOut[Windowed[String], Long]
    .withLabel("[WindowedCountDemo]")
    .withKeyValueMapper { (w, c) =>
      println(s"==> Inside KeyValueMapper: $w ~> $c")
      w.toString
    }

  import org.apache.kafka.streams.kstream.Materialized
  implicit val customMaterialized: Materialized[String, Long, ByteArrayWindowStore] =
    Materialized.as("CountsWindowStore").withCachingDisabled()
  implicit val produced: Produced[Windowed[String], Long] =
    Produced
      .keySerde(new WindowedSerdes.TimeWindowedSerde[String](Serdes.String))
      .withValueSerde(Long)

  val builder = new StreamsBuilder
  builder.stream[String, String](inputTopic)
    .groupByKey
    .windowedBy(oneMinuteTumblingWindow)
    .count
    .toStream
    //.print(stdout) FIXME: Why does this not print out counts?
    .to("windowed-output")

  val topology = builder.build
  println(topology.describe())

  import org.apache.kafka.streams.KafkaStreams
  val appId = getClass.getSimpleName.replace("$", "")
  val props = Utils.getStreamsProperties(appId)
  val ks = new KafkaStreams(topology, props)
  ks.start()
}
