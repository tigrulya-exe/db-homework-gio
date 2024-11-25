#!/bin/sh

docker compose up -d

java -DdbUser=giorgiy -DdbPassword=armyanin -DdbUrl=jdbc:postgresql://localhost:65432/Homework -jar ./build/libs/java-homework-1.0-SNAPSHOT-all.jar