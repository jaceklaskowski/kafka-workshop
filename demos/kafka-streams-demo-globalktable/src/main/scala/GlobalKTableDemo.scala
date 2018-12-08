import java.util.Properties

import org.apache.kafka.streams.scala.kstream.Materialized
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore
import org.apache.kafka.streams.state.internals.InMemoryKeyValueStore
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object GlobalKTableDemo extends App {

  import org.apache.kafka.streams.scala._
  import ImplicitConversions._
  import Serdes._

  val builder = new StreamsBuilder

  val store = Materialized.as[String, String, ByteArrayKeyValueStore]("dictionary-store")
  val gtable = builder.globalTable[String, String]("dictionary", store)
  println(gtable.queryableStoreName())

  val lines = builder.stream[String, String]("lines")

  def keyValueMapper(k: String, v: String): String = {
    val result = k
    println(s"keyValueMapper($k,$v) => $result")
    result
  }
  def joiner(v: String, gv: String): String = {
    val result = v + "<--->" + gv
    println(s"joiner($v,$gv) => $result")
    result
  }
  val joined = lines.join(gtable)(keyValueMapper, joiner)
  joined.to("joined")

  val topology = builder.build()
  println(topology.describe)

  val props = new Properties()
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "GlobalKTableDemo")
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
  val ks = new KafkaStreams(topology, props)
  ks.start()
}
