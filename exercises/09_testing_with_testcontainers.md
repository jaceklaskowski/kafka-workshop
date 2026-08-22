# Exercise 09: Integration Testing with Testcontainers

Duration: **45 mins**

## Procedure

1. Add test dependencies to `build.sbt`:

    ```scala
    libraryDependencies += "org.testcontainers" % "kafka" % "1.20.4" % Test
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ```

2. Write a `ScalaTest` spec that:
    1. Starts a `org.testcontainers.containers.KafkaContainer` in
       `beforeAll` (use a `@Container`-style lifecycle or plain
       `start()`/`stop()` in `beforeAll`/`afterAll`)
    2. Reads the container's `getBootstrapServers` and uses it to build
       producer/consumer `Properties` — the whole point is your app code
       doesn't need to know it's talking to a test container

3. Write one test: produce 3 `Order` records (reuse the (de)serializers
   from [08_custom_serialization.md](08_custom_serialization.md)) with
   **OrderProducerApp**'s logic, then consume them back with
   **OrderConsumerApp**'s logic and assert you get exactly those 3
   records back, in the order sent (single partition, single producer —
   ordering is guaranteed here)

4. Write a second test asserting **key-based ordering under
   repartitioning**: send several records under the *same* key, consume,
   and assert they arrive in send order — same guarantee, framed as a
   test of the property you actually depend on in production

5. Run the suite. Confirm each test starts a fresh, isolated broker (no
   leftover topics/data bleeding between test runs) — that's the main
   reason to reach for Testcontainers over a shared dev broker in CI

6. **Discuss**: what would you need to add to also test consumer *group*
   behavior (multiple consumer instances, rebalancing) in this style of
   test? Is it worth it, versus the exercise you already ran manually in
   [06_consumer_groups.md](06_consumer_groups.md)?
