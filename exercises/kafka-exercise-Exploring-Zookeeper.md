# Exercise: Exploring Zookeeper

## Procedure

1. Use `zookeeper-shell` to connect to the Zookeeper instance
    1. `./bin/zookeeper-shell.sh :2181`
    2. Use the official `zkCli` for a better command-line experience (and the proper JLine support)
2. Use `help`, `ls` and `get` to explore the Kafka znodes
    1. `ls /brokers/ids`
    2. `get /controller`
