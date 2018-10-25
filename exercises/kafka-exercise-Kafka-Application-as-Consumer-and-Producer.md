# Exercise: Kafka Application as Consumer and Producer

Write a new Kafka application (using Kafka Consumer and Producer APIs) that does the following:

1. Consumes messages from a topic, e.g. `input`
2. Sends the messages to another topic, e.g. `output`
    1. The names of the input and output topics hardcoded or given on the command line
3. (EXTRA) Uses the value of the messages to decide what topic to send the output messages to
    1. A record with the value of `topic-1` would be sent to `topic-1` topic
4. (EXTRA) Apply a transformation on the value, e.g. "upper" the strings

Duration: 30 mins

## Questions

1. What happens when you use the same topic as the input and the output?
2. What happens when you Ctrl+C?
