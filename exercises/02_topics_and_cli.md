# Exercise 02: Topics & CLI Tools

Duration: **30 mins**

Uses the broker started in [01_kafka_docker.md](01_kafka_docker.md).

## Procedure

1. Open a shell into the running container

    ```bash
    docker exec -it kafka bash
    ```

    All commands below run from inside the container, from `/opt/kafka`.

2. Create a topic **orders** with 3 partitions and a replication factor of 1
   (single broker, so 1 is the only valid value)

    ```bash
    bin/kafka-topics.sh --create --topic orders \
      --bootstrap-server localhost:9092 \
      --partitions 3 --replication-factor 1
    ```

3. List all topics and describe **orders**

    ```bash
    bin/kafka-topics.sh --list --bootstrap-server localhost:9092
    bin/kafka-topics.sh --describe --topic orders --bootstrap-server localhost:9092
    ```

    Identify the leader, replicas and ISR for each partition (there's only
    one broker, so all three columns should point at broker `1`).

4. Alter the topic's retention to 10 minutes (`600000` ms) using
   `--alter --config`, then confirm the change with `--describe`

    ```bash
    bin/kafka-topics.sh --alter --topic orders \
      --bootstrap-server localhost:9092 \
      --config retention.ms=600000
    bin/kafka-topics.sh --describe --topic orders --bootstrap-server localhost:9092
    ```

5. Produce a few keyed messages with `kafka-console-producer` — use
   `key:value` syntax so each key routes to a deterministic partition

    ```bash
    bin/kafka-console-producer.sh --topic orders \
      --bootstrap-server localhost:9092 \
      --property "parse.key=true" --property "key.separator=:"
    ```

    Send: `order-1:first order`, `order-2:second order`, `order-1:third order`

6. Consume from the beginning with `kafka-console-consumer`, printing keys
   and the partition each record landed on

    ```bash
    bin/kafka-console-consumer.sh --topic orders \
      --bootstrap-server localhost:9092 --from-beginning \
      --property print.key=true --property print.partition=true
    ```

    Confirm both `order-1` messages landed on the same partition (same key
    → same partition, as long as the partition count doesn't change) while
    `order-2` likely landed elsewhere.

7. Delete the topic and confirm it's gone

    ```bash
    bin/kafka-topics.sh --delete --topic orders --bootstrap-server localhost:9092
    bin/kafka-topics.sh --list --bootstrap-server localhost:9092
    ```
