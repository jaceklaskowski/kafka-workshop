organization := "pl.japila"
name := "kafka-streams-exercises"
version := "0.1"

scalaVersion := "2.12.6"

val kafkaVersion = "2.0.0"

// Main library
libraryDependencies += "org.apache.kafka" % "kafka-streams" % kafkaVersion

// Scala API for Kafka Streams
// Note two percent signs (%%) to encode Scala version
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion

// Logging (Backend)
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.8.0-beta2"
