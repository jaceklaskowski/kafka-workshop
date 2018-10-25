# Exercise: Global Word Count

Write a new Kafka application `WordCountApp` (using Kafka Producer and Consumer APIs) that does the following:

1. Consumes records from a topic, e.g. `input`
2. Counts words (in the values of records)
3. Uses `KStream.groupBy` to group by word and `count` the occurences
    1. Uses the state
4. Produces records with the unique words and their occurences (counts)

Duration: 30 mins
