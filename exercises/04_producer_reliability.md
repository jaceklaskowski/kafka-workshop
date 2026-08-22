# Exercise 04: Producer Reliability & Custom Partitioning

Duration: **45 mins**

Builds on [03_scala_producer.md](03_scala_producer.md).

## Part 1 — Callbacks and acks

1. Change `send` calls to use the overload that takes a `Callback`. Log
   the `RecordMetadata` (partition + offset) on success, and the
   `Exception` on failure

2. Set `acks=all` (via `ProducerConfig.ACKS_CONFIG`) and re-run — confirm
   behavior is unchanged against the single-broker dev cluster (with
   `replication-factor=1`, `all` and `1` behave the same; the difference
   only shows up with replicas)

3. Force a failure to see the callback's error path: point
   `bootstrap.servers` at a wrong port (e.g. `localhost:9999`), reduce
   `delivery.timeout.ms` to something short like `5000`, and confirm the
   callback receives an exception rather than the app hanging indefinitely

## Part 2 — Idempotence

4. Set `enable.idempotence=true` (it's the default since Kafka 3.0, but
   set it explicitly so it's visible in the code). Explain in your own
   words: what duplicate does this protect against, and what does it
   *not* protect against (e.g. the producer calling `send` twice for the
   same logical event)?

## Part 3 — Custom partitioner

5. Implement a `Partitioner` that routes all records with a key starting
   with `"vip-"` to partition `0`, and falls back to the default hashing
   behavior (`Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions`)
   for everything else

6. Wire it in via `ProducerConfig.PARTITIONER_CLASS_CONFIG`

7. Send a mix of `vip-*` and regular keys to a topic with 3+ partitions,
   consume with `--print.partition=true`, and confirm all `vip-*` keys
   land on partition 0
