#!/usr/bin/env bash

echo 'build the project'
mvn clean install -D skipTests

echo 'running container'
docker-compose -f docker-compose.yml up -d