# ASCLAB Micronaut PoC

This is an example of a very simplified insurance sales system made in a microservice architecture using Micronaut. 

Available functionalities:
* show products catalog
* calculate price for product based on questions defined on product and tariff definition (with MVEL engine)
* register new policy
* ...

## Architecture overview

<p align="center">
    <img alt="Micronaut Microservices Architecture" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/micronaut-microservices-architecture.png" />
</p>

* **agent-portal-gateway** - [Gateway pattern from EAA Catalog](https://martinfowler.com/eaaCatalog/gateway.html) implementation
* **payment-service** - create account for new Policy and show policy accounts list
* **payment-service-api** - DTOs, exceptions and operations (methods) for payment-service
* **policy-service** - register new Policy, close Policy and show policies list
* **policy-service-api** - DTOs, exceptions and operations (methods) for policy-service
* **pricing-service** - return price for selected product based on tariff
* **pricing-service-api** - DTOs, exceptions and operations (methods) for pricing-service
* **product-service** - simple product catalog
* **product-service-api** - DTOs, exceptions and operations (methods) for product-service
* **web-vue** - frontend

## Building
This step requires **Java 8 (JDK), Maven** and **Yarn**.

For demo purposes build process is automated by a shell script.
For Unix-based systems:
```
build-without-tests.sh
```
For Windows:
```
build-without-tests.bat
```

If you already run the necessary infrastructure (Kafka, Consul etc.), you should run build with all tests:
For Unix-based systems:
```
build.sh
```
For Windows:
```
build.bat
```


## Running

Prerequisites:
* **docker**
* **docker-compose**

For Windows users, append below line ```C:\Windows\System32\drivers\etc\hosts```:
```
127.0.0.1 kafkaserver
```

### Automated deployment

To run the whole system on local machine just type:
```
docker-run.sh
```
This script will provision required infrastructure and start all services.
Setup is powered by docker-compose and configured via `docker-compose.yml` file.

Afterwards you need to add kafka cluster - either via web UI ([Kafka Manager](http://localhost:9000/) -> Cluster -> Add Cluster)
or using provided script:
```
kafka-create-cluster.sh
```

At this point system is ready to use: [http://localhost](http://localhost)

### Manual deployment

If you want to run services manually (eg. from IDE), you have to provision infrastructure with script from ````scripts``` folder:
```
infra-run.sh
```

Afterwards you need to add kafka cluster - either via web UI ([Kafka Manager](http://localhost:9000/) -> Cluster -> Add Cluster)
or using provided script:
```
kafka-create-cluster.sh
```

* Consul dashboard: ```http://localhost:8500```
* Zipkin dashboard: ```http://localhost:9411/zipkin/```
* Kafka Manager dashboard: ```http://localhost:9000/```

#### Consul without our script
```
docker run -p 8500:8500 consul
```
#### Zipkin without our script
```
docker run -d -p 9411:9411 openzipkin/zipkin
```
#### Kafka without our script
Setup Kafka on Windows with [this instruction](https://zablo.net/blog/post/setup-apache-kafka-in-docker-on-windows).

## Add new microservice

Create new microservice with [Micronaut CLI](http://guides.micronaut.io/micronaut-cli/guide/index.html):
```
mn create-app pl.altkom.asc.lab.[SERVICE-NAME]-service -b maven
```

This command generate project in Java and Maven as build tool.

## Dashboard examples

### Tracing requests with Zipkin
<p align="center">
    <img alt="Zipkin" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/zipkin.png" />
</p>

### Show topics on Kafka
<p align="center">
    <img alt="Kafka" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/kafka.png" />
</p>

### Show registered services in Consul
<p align="center">
    <img alt="Consul" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/consul.png" />
</p>


## References
* [Micronaut Docs](https://docs.micronaut.io/latest/guide/index.html)
* [Micronaut Workshop](https://alvarosanchez.github.io/micronaut-workshop/)
* [Building microservices with Micronaut (Part I)](https://mfarache.github.io/mfarache/Building-microservices-Micronoaut/)
* [Building microservices with Micronaut (Part II)](https://mfarache.github.io/mfarache/Traceability-microservices-Micronoaut/)