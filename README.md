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


## Spring Cloud config
endpoint spring cloud config
http://localhost:9094/adventure-microservice/default

which pull config from git repo:
https://github.com/walterwhites/config-microservice-trip/blob/master/adventure-microservice.properties

POST url endpoint to refresh spring cloud config without restart application
http://localhost:9090/actuator/refresh