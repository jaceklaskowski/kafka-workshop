# Exercise 01b: Multi-Broker Kafka Cluster with Docker Compose

**Bonus exercise — not part of the 2-day agenda.** The workshop deliberately
stays at the producer/consumer client level (see [00_agenda.md](00_agenda.md),
"What's deliberately left out"). This is for anyone curious what a real,
replicated cluster looks like under the hood. Builds on
[01_kafka_docker.md](01_kafka_docker.md).

Duration: **~20 mins**

## Goal

Run 3 Kafka brokers (each also acting as a KRaft controller — "combined"
mode) with Docker Compose, create a replicated topic, and watch leader
election and ISR shrink/grow as a broker goes down and comes back.

## Set up the cluster

Create `docker-compose.yml`:

```yaml
services:
  kafka-1:
    image: apache/kafka:4.3.1
    container_name: kafka-1
    ports:
      - "29092:9092"
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://:19092,PLAINTEXT_HOST://:9092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-1:19092,PLAINTEXT_HOST://localhost:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

  kafka-2:
    image: apache/kafka:4.3.1
    container_name: kafka-2
    ports:
      - "39092:9092"
    environment:
      KAFKA_NODE_ID: 2
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://:19092,PLAINTEXT_HOST://:9092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-2:19092,PLAINTEXT_HOST://localhost:39092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

  kafka-3:
    image: apache/kafka:4.3.1
    container_name: kafka-3
    ports:
      - "49092:9092"
    environment:
      KAFKA_NODE_ID: 3
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://:19092,PLAINTEXT_HOST://:9092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-3:19092,PLAINTEXT_HOST://localhost:49092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0
```

Each node runs both the `broker` and `controller` KRaft roles ("combined"
mode) — simpler to run than dedicated controller nodes, though production
clusters usually separate them. Every node lists all three in
`KAFKA_CONTROLLER_QUORUM_VOTERS` so they can form the controller quorum.

Each broker exposes two listeners: `PLAINTEXT` for other brokers inside the
Docker network (`kafka-N:19092`) and `PLAINTEXT_HOST` for you, on the host
(`localhost:29092`/`39092`/`49092`).

<details>
<summary>Why no shared CLUSTER_ID?</summary>

All three nodes need to agree on the same KRaft cluster ID to form one
cluster. The image defaults `CLUSTER_ID` to the same fixed value
(`5L6g3nShT-eMCtK--X86sw`) on every container when the variable isn't set
explicitly, so leaving it unset here is what makes the three nodes agree —
not an oversight. Set it explicitly (same value on all three) if you want a
cluster ID other than the image's default.

</details>

Start it:

```bash
docker compose up -d
```

## Verify the cluster

```bash
docker exec -it kafka-1 bash
```

From inside the container (`/opt/kafka`):

```bash
bin/kafka-broker-api-versions.sh \
  --bootstrap-server kafka-1:19092,kafka-2:19092,kafka-3:19092 | grep -E '^kafka-[0-9]'
```

You should see one block per broker (`kafka-1:19092 (id: 1 rack: null) ->`,
etc.) — confirming all three are up and reachable.

## Create a replicated topic

```bash
bin/kafka-topics.sh --create --topic orders \
  --bootstrap-server kafka-1:19092,kafka-2:19092,kafka-3:19092 \
  --partitions 3 --replication-factor 3
```

```shell
bin/kafka-topics.sh --describe --topic orders \
  --bootstrap-server kafka-1:19092,kafka-2:19092,kafka-3:19092
```

Each partition should show a different broker as `Leader`, and `Replicas`/
`Isr` listing all three broker IDs — the replication factor 3 spreads each
partition's copies across every broker.

Produce and consume as usual.

```bash
./bin/kafka-console-producer.sh --topic orders \
  --bootstrap-server kafka-1:19092
```

```bash
./bin/kafka-console-consumer.sh --topic orders --from-beginning \
  --bootstrap-server kafka-1:19092
```

## Kill a broker and watch failover

From the host, find which broker is the current leader for partition 0 from
the `--describe` output above, then stop a *different* broker (one that's a
follower, not the leader, so you can see ISR shrink rather than a leader
election first):

```bash
docker stop kafka-2
```

Re-run `--describe` (from the shell still open in `kafka-1`, drop `kafka-2`
from the bootstrap list since it's down):

```bash
bin/kafka-topics.sh --describe --topic orders \
  --bootstrap-server kafka-1:19092,kafka-3:19092
```

Broker `2` disappears from `Isr` on any partition it was replicating, while
`Replicas` still lists it (Kafka remembers the assignment, it just isn't
in-sync). If broker `2` happened to be a partition's leader, a different
in-sync replica takes over and `Leader` changes.

Producing/consuming still works — replication factor 3 with 2 brokers left
tolerates one failure.

## Bring it back

```bash
docker start kafka-2
```

Re-run `--describe` again after a few seconds; broker `2` rejoins the `Isr`
list once it catches up.

## Cleanup

```bash
docker compose down
```

The compose file above doesn't mount any volumes, so each broker's KRaft
storage lives only in its container — removing the containers is enough for
a re-run to start from a clean cluster.
