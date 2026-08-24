# Exercise 01: Running Apache Kafka with Docker

Instructions for running `apache/kafka:4.3.1` from Docker Hub:
https://hub.docker.com/layers/apache/kafka/4.3.1

## Pull and run

```shell
docker pull apache/kafka:4.3.1
```

```shell
docker run --rm --name kafka -p 9092:9092 apache/kafka:4.3.1
```

This starts a single-node Kafka broker in KRaft mode.

The container's default config listens on `PLAINTEXT://localhost:9092`, so from the host you can connect with `localhost:9092`.

<details>
<summary>Naming conflict?</summary>

If `docker run` fails with `Conflict. The container name "/kafka" is already
in use by container ...`, a container called `kafka` already exists from a
previous run (stopped or running).

Remove it first, then re-run.

```bash
docker rm -f kafka
```

</details>

## Quick Smoke Test

### Send Message

```shell
docker exec -it kafka \
  /opt/kafka/bin/kafka-console-producer.sh \
  --topic test \
  --bootstrap-server localhost:9092
```

### Receive Message

Open a separate terminal and run the following:

```shell
docker exec -it kafka \
  /opt/kafka/bin/kafka-console-consumer.sh \
  --topic test \
  --from-beginning \
  --bootstrap-server localhost:9092
```

## Connecting from other containers/hosts

If you need to connect from other containers or hosts (not just localhost),
override the advertised listeners since the default is `localhost`-only:

```bash
docker run -d --name kafka -p 9092:9092 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://<your-host-ip-or-hostname>:9092,CONTROLLER://localhost:9093 \
  apache/kafka:4.3.1
```

Exact env var names/defaults can shift slightly between versions — check the
"How to use this image" section on the Docker Hub page for 4.3.1 specifics if
the advertised-listener override doesn't take effect.
