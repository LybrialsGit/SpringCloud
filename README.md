# Spring

The purpose of this project is to show an example on how to setup and configure spring 
services in a cloud infrastructure.

#### Technologies

- Spring Cloud Config for configuration
- Spring Cloud Netflix Eureka for service discovery
- Spring Cloud Netflix Zuul as API gateway
- Spring Cloud Netflix Feign as declarative web client
- Spring Cloud Netflix Ribbon as client side loadbalancer
- Spring Cloud Netflix Hystrix for fault tolerance
- Spring Boot Actuator for config refresh
- Spring Cloud Bus for automatic config refresh using apache kafka
- Spring Cloud Sleuth for tracing
- Spring Cloud Zipkin for tracing

#### Scenario

This is a fictional scenario which does not really make much sense.

It contains a user service which provides a REST-Endpoint to return 
user information in XML format.

It also contains a user transformation service which provides a REST-Endpoint
to call the user service and transform the XML result into JSON.

The users are returned ordered by their first name, last name or age. This 
is configured using the configuration service (As I said, this does not make
much sense, it is just an example and it focuses on the cloud infrastructure).

## Services

### Zookeeper

```yaml
host: localhost
port: 2181
```

### Kafka

```yaml
host: localhost
ports:
  - 9092
  - 29092
```

### Spring Cloud Config Server

```yaml
host: localhost
port: 8888
```

In this project it uses: `https://github.com/LybrialsGit/config-repository.git`
as config repository. If you want to change that you need to adjust the `application.yml`
file in the `config-server` project to point to a different repository.

### Spring Cloud Eureka Server

```yaml
host: locahost
port: 8761
```

### Spring Cloud Zuul Gateway

```yaml
host: localhost
port: 8765
```

### User-Service

```yaml
host: localhost
ports:
  - 8000  # instance 1
  - 8001  # instance 2
  - 8002  # instance 3
```

### User-Transformation-Service

```yaml
host: localhost
port: 8100
```

Uses `Feign` to call the user service via `Zuul` API gateway and `Ribbon` for 
client side load balancing. You can see in the logs of the user services which 
of the three user service instances is getting called (attach to docker logs).

### Zipkin Server

```yaml
host: localhost
port: 9411
```

## Starting the services

```shell script
docker-compose -f docker-compose-kafka.yml up -d --build
docker-compose -f docker-compose-cloud.yml up -d --build
docker-compose -f docker-compose-zipkin.yml up -d --build
docker-compose -f docker-compose-services.yml up -d --build
```

Wait in between until the services are started.

If you want to see the logs remove the parameter `-d` (for detached) or attach 
manually using: `docker-compose logs -f <container-name>`.

## Updating the configuration

If you want to change the configuration first commit and push your changes to the
configuration repository. After that you can either update each services config 
individually by making a `POST` to each service, for example:

```shell script
http://localhost:8000/actuator/refresh
```

Or you can make a bus refresh to update the configuration of all services at once:

```shell script
http://localhost:8888/actuator/bus-refresh
```

The `bus-refresh` endpoint can be called on any service. It will automatically
trigger the configuration refresh of all services (using kafka and spring cloud bus).

## Using Zuul gateway

Call:

```shell script
http://localhost:8765/user-service/users
http://localhost:8765/user-transformation-service/users
```

## Trace requests

Call:

```shell script
http://localhost:9411
```

And search for trace ID's.