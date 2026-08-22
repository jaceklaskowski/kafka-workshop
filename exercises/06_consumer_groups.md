# Exercise 06: Consumer Groups in Action

Duration: **30 mins**

In this exercise you'll run multiple instances of **OrderConsumerApp**
(from [05_kafka_consumer.md](05_kafka_consumer.md)) in the same consumer
group and watch partitions get assigned, rebalanced and reclaimed.

## Procedure

1. Start a Kafka cluster ([01_kafka_docker.md](01_kafka_docker.md))

2. Recreate topic **orders** with 3 partitions (delete and re-create if it
   has a different partition count already)

   ```shell
   docker exec -it kafka bash
   ```

   ```shell
   cd /opt/kafka
   ```

   ```shell
   ./bin/kafka-topics.sh --bootstrap-server :9092 --create --topic t1 --partitions 3
   ```

3. Start **OrderConsumerApp** once — call this instance **c1** — with
   `group.id=CG1`. It should be assigned all 3 partitions.

   ```shell
   ./bin/kafka-console-consumer.sh --bootstrap-server :9092 --topic t1 --command-property group.id=CG1
   ```

4. Run **OrderProducerApp** to send a batch of keyed records. Confirm
   **c1** receives all of them.

   ```shell
   kcat -P -b localhost:9092 -t t1 -p 0 -K :
   ```

   (`-K` is for a key-value separator)

5. Start a second instance of **OrderConsumerApp** — **c2** — same
   `group.id=CG1`. Watch the logs (`ConsumerRebalanceListener` events, or
   just the change in which partitions each instance's `poll` returns
   records for): the 3 partitions should now be split across **c1** and
   **c2**.

   ```shell
   kcat -P -b 0.0.0.0:9092 -t t1 -p 2 -K :
   ```

6. Send another batch of keyed records and confirm messages are split
   between **c1** and **c2** roughly by partition, not by round-robin
   per-message.

7. Start a third instance — **c3** — same group. Send more records.
   With 3 partitions and 3 consumers, each should now own exactly one.

8. Start a **fourth** instance — **c4** — same group, and send more
   records. **Predict** first: does c4 receive anything?
   Confirm: a consumer group can have at most as many *active* consumers as there
   are partitions — extra consumers sit idle.

9. Kill **c2** (Ctrl+C) and send another batch. Observe which of the
   remaining consumers picks up the orphaned partition(s), and how long
   the rebalance takes to happen (governed by `session.timeout.ms` /
   `heartbeat.interval.ms` for the abrupt-kill case).

10. **Discuss**: what's the practical implication for scaling a consumer
   application horizontally? (Partition count is a hard ceiling on
   effective parallelism per consumer group.)
