import java.util.Properties

import org.apache.kafka.streams.scala.Serdes

object Utils {
  def getStreamsProperties(appId: String, brokers: String = "localhost:9092"): Properties = {
    val props = new Properties()
    import org.apache.kafka.streams.StreamsConfig
    props.setProperty(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1")
    props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props
  }
}
