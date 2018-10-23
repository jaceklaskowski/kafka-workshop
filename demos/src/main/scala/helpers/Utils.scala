package helpers

import java.util.Properties

object Utils {
  def getStreamsProperties(appId: String, brokers: String = "localhost:9092"): Properties = {
    val props = new Properties()
    import org.apache.kafka.streams.StreamsConfig

    import scala.concurrent.duration._
    props.setProperty(
      StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10.seconds.toMillis.toString)
    props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    //props.setProperty(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1.toString)
    props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    import org.apache.kafka.streams.scala.Serdes
    props.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.setProperty(
      StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    import org.apache.kafka.common.metrics.Sensor
    props.setProperty(
      StreamsConfig.METRICS_RECORDING_LEVEL_CONFIG, Sensor.RecordingLevel.DEBUG.toString)
    props.setProperty(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0.toString)
    import org.apache.kafka.clients.consumer.ConsumerConfig
    props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
    props.setProperty(
      StreamsConfig.DEFAULT_WINDOWED_KEY_SERDE_INNER_CLASS, Serdes.String.getClass.getName)
    props.setProperty(
      StreamsConfig.DEFAULT_WINDOWED_VALUE_SERDE_INNER_CLASS, Serdes.String.getClass.getName)
    props
  }
}
