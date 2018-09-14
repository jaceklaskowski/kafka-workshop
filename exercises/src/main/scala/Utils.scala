import java.util.Properties

import org.apache.kafka.common.serialization.Serdes.StringSerde

object Utils {
  def getStreamsProperties(appId: String): Properties = {
    val props = new Properties()
    import org.apache.kafka.streams.StreamsConfig
    props.setProperty(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1")
    props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    props.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[StringSerde].getName)
    props
  }
}
