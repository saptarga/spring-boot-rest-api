#!/usr/bin/env bash

echo 'Stopping containers'
docker-compose -f docker-compose.yml down

read -p "Do you want to remove created image ? (y/n) " option

if [[ ${option} == y ]]
then
    docker image rm privyid-pretest-backend-enginer_api-service
fi