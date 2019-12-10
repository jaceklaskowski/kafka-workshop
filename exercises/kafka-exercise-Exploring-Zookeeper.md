# Exercise: Exploring Zookeeper

This exercise is to give you time to explore the very depths of a Kafka cluster, i.e. Zookeeper.

Zookeeper is used as a _runtime configuration database_ by Kafka brokers given the following features:

* File system-like tree structure with **znodes** and optional **metadata** attached
* Ephemeral znodes

Duration: **30 mins**

**TIP** Use [bit.ly/kafka-exercise-admin-02](https://bit.ly/kafka-exercise-admin-02) to access this exercise.

## Procedure

1. Use **zookeeper-shell** to connect to the Zookeeper instance
    * **./bin/zookeeper-shell.sh :2181**
2. Use **help** to learn about available commands
3. Use **ls** and **get** commands to explore the Kafka znodes
    * **ls /brokers/ids**
    * **get /brokers/ids/0**
    * **get /controller**

**NOTE:** `zookeeper-shell` comes with no support for command history and so consider the official [Apache Zookeeper](https://zookeeper.apache.org/)'s **zkCli** for a better command-line experience (and command history).
