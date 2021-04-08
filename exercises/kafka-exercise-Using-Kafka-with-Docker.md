# Exercise: Using Kafka with Docker

## Procedure

1. `git clone` [kafka-docker](https://github.com/wurstmeister/kafka-docker) project
2. Follow the steps at [Pre-Requisites](https://github.com/wurstmeister/kafka-docker#pre-requisites) and [Usage](https://github.com/wurstmeister/kafka-docker#usage)
    1. `docker-compose up -d`
    1. `docker-compose scale kafka=3`
3. Send messages from within a Docker container
    1. `docker-compose ps` for active service containers
    1. `docker-compose exec kafka bash` to attach to one
4. Shut down the containers and remove all stopped containers
    1. `docker-compose stop`
    1. `docker container prune`
