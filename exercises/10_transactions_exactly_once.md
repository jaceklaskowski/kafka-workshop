# Exercise 10 (Stretch): Exactly-Once with Transactions

Duration: **30–45 mins**

Optional — only if Day 2 is ahead of schedule. Read [KafkaProducer](https://kafka.apache.org/40/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)'s
section on transactions first.

## Procedure

1. Set `transactional.id` on a producer's `Properties` (any stable
   unique string) and call `initTransactions()` once, right after
   construction

2. Wrap a batch of `send` calls in `beginTransaction()` /
   `commitTransaction()`. Consume with `isolation.level=read_committed`
   on the consumer side and confirm the batch appears atomically — all
   records visible together, not one at a time as they're sent

3. Force an abort: call `send` a few times, then `abortTransaction()`
   instead of committing. Confirm a `read_committed` consumer sees
   **none** of those records — while a consumer left on the default
   `read_uncommitted` sees them anyway. This is the crux of what
   "transactional" buys you: it's opt-in on the read side.

4. Simulate a crash mid-transaction: start a transaction, send a couple
   of records, then kill the JVM (`System.exit(1)`) before calling
   commit or abort. Restart a producer with the **same**
   `transactional.id` and call `initTransactions()` — this is what
   fences off and rolls back the previous incomplete transaction. Confirm
   the abandoned records never become visible to a `read_committed`
   consumer.

5. **Discuss**: this exercise only covered producer-side transactions
   ("send exactly once"). True end-to-end exactly-once — consume,
   process, and produce as one atomic unit — needs the consume offsets
   committed *as part of* the same transaction
   (`producer.sendOffsetsToTransaction`). That combination is exactly
   what Kafka Streams does for you automatically, which is one of the
   main reasons applications reach for it once their pipeline gets past
   "one input topic, one output topic."
