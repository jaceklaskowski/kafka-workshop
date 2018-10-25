# Exercise: Using Kafka with Docker

## Procedure

1. Use [kafka-docker](https://github.com/wurstmeister/kafka-docker) Docker image
2. Follow the steps at [Pre-Requisites](https://github.com/wurstmeister/kafka-docker#pre-requisites) and [Usage](https://github.com/wurstmeister/kafka-docker#usage)
    1. `docker-compose -f docker-compose-single-broker.yml up`
3. Send messages from within a Docker container
    1. Use `docker ps` to know the IDs of active containers
    2. `docker exec -it [ID] /bin/bash` to attach to an active container
4. Shut down the containers and remove all stopped containers
    1. `docker container prune`
