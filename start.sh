#!/bin/bash
gradle bootJar
docker-compose up --build  -d