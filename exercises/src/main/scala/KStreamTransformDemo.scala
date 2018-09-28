import helpers.Utils
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.Transformer
import org.apache.kafka.streams.processor.ProcessorContext
import org.apache.kafka.streams.state.{KeyValueStore, Stores}

/**
  * Use kafka-console-consumer to show the output
  *
  * ./bin/kafka-console-consumer.sh --topic transform-output \
  *   --bootstrap-server localhost:9092 \
  *   --from-beginning \
  *   --value-deserializer org.apache.kafka.common.serialization.LongDeserializer \
  *   --property print.key=true
  */
object KStreamTransformDemo extends App {

  val inputTopic = "transform-input"
  val outputTopic = "transform-output"
  val storeName = "counts"
  val appId = getClass.getSimpleName.replace("$", "")

  import org.apache.kafka.streams.scala._
  import ImplicitConversions._
  import Serdes._

  class MyTransformer(storeName: String) extends Transformer[String, String, (String, Long)] {
    var store: KeyValueStore[String, Long] = _

    override def init(context: ProcessorContext): Unit = {
      println("MyTransformer.init")
      this.store = context.getStateStore(storeName).asInstanceOf[KeyValueStore[String, Long]]
    }

    override def transform(key: String, value: String): (String, Long) = {
      // Using value as key
      println(s"--> MyTransformer.transform(key=$key, value=$value)")
      val count = Option(store.get(value)).map(_ + 1).getOrElse(1L)
      store.put(value, count)
      println(s"<-- MyTransformer.transform => (key=$value, value=$count)")
      (value, count)
    }

    override def close(): Unit = ???
  }

  val builder = new StreamsBuilder
  val kvStore = Stores.keyValueStoreBuilder(
    Stores.inMemoryKeyValueStore(storeName),
    Serdes.String,
    Serdes.Long)
  builder.addStateStore(kvStore)
  builder.stream[String, String](inputTopic)
    .transform[String, Long](new MyTransformer(storeName), storeName)
    .to(outputTopic)

  val topology = builder.build

  val ks = new KafkaStreams(topology, Utils.getStreamsProperties(appId))
  ks.start()
}
