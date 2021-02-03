#!/bin/bash
set -e

echo "Starting SSH ..."
service ssh start

java  -XX:+UnlockExperimentalVMOptions  -Djava.security.egd=file:/dev/./urandom -jar /app/nova-api.jar