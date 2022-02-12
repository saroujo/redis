#!/bin/sh

mvn clean install

docker-compose down

docker image prune -a

docker-compose up