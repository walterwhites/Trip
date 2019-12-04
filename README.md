# Trip
Java Trip Application (microservices architecture)

Project url: https://openclassrooms.com/fr/projects/186/assignment

Swagger HTML doc: http://localhost:9090/swagger-ui.html#/adventure-controller
Swagger JSON doc: http://localhost:9090/v2/api-docs

CI : Travis https://travis-ci.com/walterwhites/Trip/builds

Applications port listening:
- adventure-microservice = 9090
- category-microservice = 9091
- clientui-microservice = 9092
- payment-microservice = 9093
- config-server-microservice = 9094
- Eureka server = 9095
- Zuul server = 9096

## Spring Cloud config
endpoint spring cloud config
http://localhost:9094/adventure-microservice/default

which pull config from git repo:
https://github.com/walterwhites/config-microservice-trip/blob/master/adventure-microservice.properties

POST url endpoint to refresh spring cloud config without restart application
http://localhost:9090/actuator/refresh


## Eureka server
access to Eureka dashboard with instances list: http://localhost:9095/

## Zipkin
run java -jar zipkin.jar
access to all requests datas on http://localhost:9411/

## Zuul Server
Api Gateway, which call other microservices API
http://localhost:9096/{{microservice-name}}/{{endpoint}}/

Exemple to call adventure endpoint:
http://localhost:9096/adventure-microservice/adventures/

A specific adventure:
http://localhost:9096/adventure-microservice/adventures/1

## Other informations
- All microservices use Actuator library which allow to expose endpoint that Eureka Server use to know microservices's health:
Exemple, Eureka call http://localhost:9096/actuator to know Zuul microservice's health