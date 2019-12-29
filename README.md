# Trip
Java Trip Application (microservices architecture)

Project url: https://openclassrooms.com/fr/projects/186/assignment

Swagger HTML doc: http://localhost:9090/swagger-ui.html#/adventure-controller
Swagger JSON doc: http://localhost:9090/v2/api-docs

CI : Travis https://travis-ci.com/walterwhites/Trip/builds

Applications port listening:
- adventure-microservice = 9090, 9011
- category-microservice = 9091
- clientui-microservice = 9092
- payment-microservice = 9093
- config-server-microservice = 9094
- Eureka server = 9095
- Zuul server = 9096, 9099, 9210, 9211
- login-microservice = 9097
- client-microservice = 9098
- comment-microservice = 9061

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

## Db init
We use Postgres, schema and data file are provided inside resources folders,
the only requirement is to have these Postgres Db or create them:
- createdb adventures
- createdb category
- createdb client
- createdb payment

## Debug the app
DebugUtils class provide many methods to debug the code, requests etc.
exemple to get trace url of a request with Ziplkin
```
ZipkinDebug.displayTraceUrl(request);
```
get a request header info
```
RequestInfo.displayRequestHeader(request, "X-Forwarded-Host");
```