#!/bin/bash
sh stop-app.sh &&
cd config-server-microservice && nohup mvn spring-boot:run &
sleep 20 && cd eureka-microservice && nohup mvn spring-boot:run &
sleep 20 && cd zuul-server-microservice && nohup mvn spring-boot:run &
sleep 40 && cd category-microservice && nohup mvn spring-boot:run &
sleep 40 && cd adventure-microservice && nohup mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090 &
sleep 40 && cd adventure-microservice && nohup mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9011 &
sleep 40 && cd login-microservice && nohup mvn spring-boot:run &
sleep 40 && cd client-microservice && nohup mvn spring-boot:run &
sleep 40 && cd clientui-microservice && nohup mvn spring-boot:run &