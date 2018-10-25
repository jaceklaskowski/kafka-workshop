# Exercise: Using Custom Keys

Write a new Kafka application `CustomClassForKeyDemo` (using Kafka Producer API) as follows:

1. Write a custom class with two properties, e.g. `instID` and `empID`
    1. `case class InstEmp`
2. Implement a custom `Serializer` for the class
    1. Read [Serializer](http://kafka.apache.org/20/javadoc/org/apache/kafka/common/serialization/Serializer.html)
    2. `class InstEmpSerializer extends Serializer`
3. Write a Kafka producer that uses the class for keys and the serializer
    1. Use `ProducerConfig.KEY_SERIALIZER` property to register the serializer
    2. Use `InstEmp` for the keys of `ProducerRecord` objects (and any type for values)
4. Use `kafka-console-consumer` to print out the records

Duration: 30 mins
