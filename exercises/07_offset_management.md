# Exercise 07: Manual Offset Commits

Duration: **40 mins**

Builds on [05_scala_consumer.md](05_scala_consumer.md).

## Part 1 — Turn off auto-commit

1. Set `enable.auto.commit=false` on **OrderConsumerApp**

2. Run it against a topic with existing data (with a fresh `group.id`,
   `auto.offset.reset=earliest`). Without any commit call, **predict**
   then confirm: restarting the app re-reads everything from the start,
   every single time — because nothing was ever committed.

## Part 2 — Commit synchronously, after processing

3. After each `poll()` batch is fully processed (printed), call
   `consumer.commitSync()`

4. Restart the app mid-stream (kill it after processing part of a large
   batch, before sending more records) and confirm on restart it resumes
   roughly where it left off, not from the beginning

5. **Discuss**: where exactly should `commitSync()` go relative to your
   processing logic to get **at-least-once** semantics? What would move
   it to (accidentally) **at-most-once**? (Commit *before* processing
   drops records on a crash between commit and processing; commit *after*
   successful processing can reprocess a record on crash, but never
   silently drops one.)

## Part 3 — Commit asynchronously with a callback

6. Swap `commitSync()` for `commitAsync()` with an `OffsetCommitCallback`
   that logs failures. Explain why `commitAsync` alone is not safe to use
   right before shutdown (the callback may not have fired yet) — and fix
   it by calling `commitSync()` one last time in a `finally` block around
   `consumer.close()`

## Part 4 — Rebalance listener

7. Implement a `ConsumerRebalanceListener` and pass it to `subscribe`.
   In `onPartitionsRevoked`, commit any pending offsets *before* the
   partitions are handed to another consumer. Log both callback methods
   so you can see them fire during the multi-instance scenario from
   [06_consumer_groups.md](06_consumer_groups.md).
