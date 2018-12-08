# Demo: GlobalKTable

Start the following commands in their own terminals.

```bash
$ ./bin/kafka-console-producer.sh \
  --broker-list :9092 \
  --topic dictionary \
  --property parse.key=true \
  --property key.separator=,

$ ./bin/kafka-console-producer.sh \
  --broker-list :9092 \
  --topic lines \
  --property parse.key=true \
  --property key.separator=,

$ ./bin/kafka-console-consumer.sh \
  --topic joined \
  --bootstrap-server :9092 \
  --property print.key=true
```