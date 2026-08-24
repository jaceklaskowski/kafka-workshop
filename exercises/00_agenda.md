---
marp: true
paginate: true
---

# Apache Kafka for Scala/Java Developers

<!-- _paginate: skip -->
<!-- _theme: gaia -->

## 2-Day Agenda

---

## Audience

* Scala/Java developers
* comfortable with sbt and IntelliJ IDEA
* (optional) familiar with Docker and Docker Compose
* **no prior Kafka experience assumed**

---

## Covered

This workshop stays at the producer/consumer client level.

Just enough broker/topic theory to build correct, reliable clients.

---

## Development Setup

Broker: `apache/kafka:4.3.1` via Docker or standalone.

Client library: `kafka-clients` (match the broker version, `4.3.1`)

Scala 2.13 or 3.x — sbt defaults are fine.

---

## Out of Scope

* No Kafka Connect
* No Kafka Streams

---

## Theory vs. practice

Roughly **30% theory / 70% hands-on**, delivered as short cycles rather
than one long lecture per day: 20–30 min of theory immediately followed by
a 30–60 min exercise that applies it. Concepts land better when there's
code running within minutes of hearing about them, and it keeps energy up
over two full days. Timings below are guidance, not a strict clock — leave
room to run long on whichever exercise the group struggles with; something
has to give, and it's usually the stretch goals at the end of each day.

---

## Day 1 — Kafka Fundamentals & Producers

### Before the break — Fundamentals

| # | Block | Type | ~Duration |
|---|-------|------|-----------|
| 1 | Kafka architecture: brokers, topics, partitions, replication, ISR, KRaft | Theory | 30 min |
| 2 | [Exercise 01: Running Kafka with Docker](01_kafka_docker.md) | Practice | 15 min |
| 3 | Topics in depth: partitions & replication factor, retention, CLI tooling | Theory | 20 min |
| 4 | [Exercise 02: Topics & CLI Tools](02_topics_and_cli.md) | Practice | 30 min |

Subtotal: ~50 min theory / ~45 min practice (~1h35).

---

## 🍽️ Lunch Break (Day 1) — 1 hour

---

### After the break — Producers

| # | Block | Type | ~Duration |
|---|-------|------|-----------|
| 5 | Producer API: `ProducerRecord`, partitioning, serializers, batching | Theory | 30 min |
| 6 | [Exercise 03: Your First Kafka Producer](03_kafka_producer.md) | Practice | 30 min |
| 7 | Producer reliability: `acks`, retries, idempotence, callbacks, custom partitioners | Theory | 20 min |
| 8 | [Exercise 04: Producer Reliability & Custom Partitioning](04_producer_reliability.md) | Practice | 45 min |

Subtotal: ~50 min theory / ~1h30 practice (~2h20).

Day 1 total: ~1h40 theory / ~2h15 practice (~3h55 content), plus the lunch
break and shorter Q&A/coffee breaks.

---

## Day 2 — Consumers, Offsets & Testing

### Before the break — Consumers & Consumer Groups

| # | Block | Type | ~Duration |
|---|-------|------|-----------|
| 1 | Consumer API: the poll loop, deserializers, consumer configuration | Theory | 30 min |
| 2 | [Exercise 05: Your First Kafka Consumer](05_kafka_consumer.md) | Practice | 30 min |
| 3 | Consumer groups & partition rebalancing (eager vs. cooperative-sticky) | Theory | 25 min |
| 4 | [Exercise 06: Consumer Groups in Action](06_consumer_groups.md) | Practice | 30 min |

Subtotal: ~55 min theory / ~1h15 practice (~2h10).

---

## Bonus, if time allows

[Exercise 01b: Multi-Broker Kafka Cluster with Docker Compose](01b_kafka_docker_multi_broker.md)

---

## 🍽️ Lunch Break (Day 2) — 1 hour

---

### After the break — Offsets, Serialization & Testing

| # | Block | Type | ~Duration |
|---|-------|------|-----------|
| 5 | Offset management: auto-commit vs. manual commit, delivery semantics | Theory | 20 min |
| 6 | [Exercise 07: Manual Offset Commits](07_offset_management.md) | Practice | 40 min |
| 7 | Serialization beyond strings: custom (de)serializers for case classes | Theory | 20 min |
| 8 | [Exercise 08: Custom Serialization](08_custom_serialization.md) | Practice | 40 min |
| 9 | Testing Kafka applications: embedded brokers vs. Testcontainers | Theory | 20 min |
| 10 | [Exercise 09: Integration Testing with Testcontainers](09_testing_with_testcontainers.md) | Practice | 45 min |

Subtotal: ~1h00 theory / ~2h05 practice (~3h05, excl. stretch).

Day 2 total: ~1h55 theory / ~3h practice (~5h15 content, excl. stretch),
plus the lunch break and shorter Q&A/coffee breaks.

---

## What's deliberately left out

* **Kafka Connect** and **Kafka Streams** — separate workshops (see the
  [top-level README](../README.md)).
* **Schema Registry / Avro** — mentioned in passing during Exercise 08 as
  "what you'd reach for in production," but not built hands-on; it pulls
  in Confluent-specific tooling that's a distraction for a 2-day
  client-focused course.
* **Broker administration / multi-broker cluster setup** — covered only
  to the depth a developer needs (what partitions/replication mean for
  the clients they write), not operator-level cluster configuration.
