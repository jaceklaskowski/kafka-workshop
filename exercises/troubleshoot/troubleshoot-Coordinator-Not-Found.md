# Trouble: Coordinator Not Found
  
## Procedure  
  
 1. Create a Kafka cluster as you did in [kafka-exercise-Using-Kafka-with-Docker.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Using-Kafka-with-Docker.md "kafka-exercise-Using-Kafka-with-Docker.md")
 2. Create a producer as you did in [kafka-exercise-Developing-Kafka-Producer.md](https://github.com/jaceklaskowski/kafka-workshop/blob/master/exercises/kafka-exercise-Developing-Kafka-Producer.md "kafka-exercise-Developing-Kafka-Producer.md")
 3.  Re-produce the below logs taken from the consumer logs:
>...
>2019-06-12 15:16:05 DEBUG AbstractCoordinator:183 - [Consumer clientId=consumer-1, groupId=test-group] Sending FindCoordinator request to broker `server b` (id: 1001 rack: null)
2019-06-12 15:16:05 DEBUG AbstractCoordinator:183 - [Consumer clientId=consumer-1, groupId=test-group] Received FindCoordinator response ClientResponse(receivedTimeMs=1560341765004, latencyMs=2, disconnected=false, requestHeader=RequestHeader(apiKey=FIND_COORDINATOR, apiVersion=1, clientId=consumer-1, correlationId=126), responseBody=FindCoordinatorResponse(throttleTimeMs=0, errorMessage='null', error=NONE, node=`server a` (id: 1002 rack: null)))
2019-06-12 15:16:05 INFO  AbstractCoordinator:341 - [Consumer clientId=consumer-1, groupId=test-group] Discovered group coordinator `server a` (id: 2147482645 rack: null)
2019-06-12 15:16:05 INFO  AbstractCoordinator:341 - [Consumer clientId=consumer-1, groupId=test-group] Marking the coordinator `server a` (id: 2147482645 rack: null) dead
2019-06-12 15:16:05 DEBUG AbstractCoordinator:183 - [Consumer clientId=consumer-1, groupId=test-group] Sending FindCoordinator request to broker `server b` (id: 1001 rack: null)
2019-06-12 15:16:05 DEBUG AbstractCoordinator:183 - [Consumer clientId=consumer-1, groupId=test-group] Received FindCoordinator response ClientResponse(receivedTimeMs=1560341765107, latencyMs=2, disconnected=false, requestHeader=RequestHeader(apiKey=FIND_COORDINATOR, apiVersion=1, clientId=consumer-1, correlationId=127), responseBody=FindCoordinatorResponse(throttleTimeMs=0, errorMessage='null', error=NONE, node=`server a` (id: 1002 rack: null)))
2019-06-12 15:16:05 INFO  AbstractCoordinator:341 - [Consumer clientId=consumer-1, groupId=test-group] Discovered group coordinator `server a` (id: 2147482645 rack: null)
2019-06-12 15:16:05 INFO  AbstractCoordinator:341 - [Consumer clientId=consumer-1, groupId=test-group] Marking the coordinator `server a` (id: 2147482645 rack: null) dead
>...

## Further Reading and Learning
 1. Coordinator
 2. Leader election