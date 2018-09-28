package helpers

trait KafkaStreamsApp extends App {

  import org.apache.kafka.streams.scala._

  val appId = getClass.getSimpleName.replace("$", "")
  val builder = new StreamsBuilder
  val topology = builder.build
  val props = Utils.getStreamsProperties(appId)

  import org.apache.kafka.streams.KafkaStreams
  val ks = new KafkaStreams(topology, props)
}
