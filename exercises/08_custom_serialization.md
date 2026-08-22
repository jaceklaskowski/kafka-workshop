# Exercise 08: Custom Serialization

Duration: **40 mins**

So far every exercise has used `StringSerializer`/`StringDeserializer`.
Real applications usually send structured data.

## Procedure

1. Define a case class:

    ```scala
    case class Order(orderId: String, customerId: String, amount: BigDecimal)
    ```

2. Pick a wire format and write a `Serializer[Order]` and a matching
   `Deserializer[Order]`:
    - Simplest: hand-roll JSON with a tiny library (e.g. `upickle` or
      `circe`), serialize to/from `Array[Byte]` via `UTF-8` strings
    - If you'd rather not pull in a JSON library: a manual
      delimiter-based encoding (`orderId|customerId|amount`) works fine
      for the exercise — the point is the `Serializer`/`Deserializer`
      wiring, not the encoding itself

3. Wire your `Serializer`/`Deserializer` classes into producer/consumer
   configs via `VALUE_SERIALIZER_CLASS_CONFIG` /
   `VALUE_DESERIALIZER_CLASS_CONFIG` (keep `String` for the key)

4. Update **OrderProducerApp** to send real `Order` values and
   **OrderConsumerApp** to print the deserialized `Order`

5. Break it on purpose: publish one malformed record with the plain
   `kafka-console-producer` (so it doesn't match your encoding), then
   run the consumer and observe what happens to a `Deserializer` that
   throws — the consumer's `poll()` call fails and, without handling,
   the app gets stuck unable to progress past that offset

6. Fix it: wrap the decode step so a bad record is logged and skipped
   instead of crashing the consumer loop

7. **Discuss**: what does a production system reach for here instead of
   hand-rolled JSON? (Avro or Protobuf with a Schema Registry — enforces
   a compatible schema across producers/consumers so step 5's "malformed
   record" can't happen in the first place. Out of scope for this
   workshop, but worth knowing it exists.)
