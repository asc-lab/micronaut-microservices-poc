# ASCLAB Micronaut PoC - LAB Insurance Sales Portal

![Build Status](https://github.com/asc-lab/micronaut-microservices-poc/workflows/Java%20CI%20with%20Maven/badge.svg)

This is an example of a very simplified insurance sales system made in a microservice architecture using Micronaut.

Comprehensive guide describing exactly the architecture, applied design patterns and technologies can be found on our blog in article **[Building Microservices with Micronaut](https://altkomsoftware.pl/en/blog/microservices-micronaut/)**.

**We have recently upgraded to Java 14 (you must have JDK14 in order to build and run the project).**

**We encourage you to read, because in this README there is only a substitute for all information.**

Other articles around microservices that could be interesting:
- [Building Business Dashboards with Micronaut and Elasticsearch Aggregations Framework](https://altkomsoftware.pl/en/blog/business-dashboards/)
- [Simplify Data Access Code With Micronaut Data](https://altkomsoftware.pl/en/blog/micronaut-data/)
- [Micronaut with RabbitMQ integration](https://altkomsoftware.pl/en/blog/micronaut-rabbitmq/)
- [CQRS and Event Sourcing Intro For Developers](https://altkomsoftware.pl/en/blog/cqrs-event-sourcing/)
- [From monolith to microservices – to migrate or not to migrate?](https://altkomsoftware.pl/en/blog/monolith-microservices/)
- [Event Storming — innovation in IT projects](https://altkomsoftware.pl/en/blog/event-storming/)
- [\[SLIDES\] Building microservices with Micronaut - practical approach](https://docs.google.com/presentation/d/1WSp3S1zFTKxN1_9kli5J9xlTWrliVjIH8E-eNFb1ghM/edit?usp=sharing)

## Architecture overview

<p align="center">
    <img alt="Micronaut Microservices Architecture" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/micronaut-microservices-architecture.png" />
</p>

* **agent-portal-gateway** - Gateway pattern from EAA Catalog implementation. \
The complexity of “business microservices” will be hidden by using Gateway pattern. This component is responsible for the proper redirection of requests to the appropriate services based on the configuration. The frontend application will only communicate with this component. This component show usage of non-blocking http declarative clients.

* **payment-service** - main responsibilities: create Policy Account, show Policy Account list, register in payments from bank statement file. \
This module is taking care of a managing policy accounts. Once the policy is created, an account is created in this service with expected money income.  Payment-service also has an implementation of a scheduled process where CSV file with payments is imported and payments are assigned to policy accounts. This component shows asynchronous communication between services using Kafka and ability to create background jobs using Micronaut. It also features accessing database using JPA.

* **policy-service** - creates offers, converts offers to insurance policies. \
In this service we demonstrated usage of CQRS pattern for better read/write operation isolation. This service demonstrates two ways of communication between services: synchronous REST based calls to `pricing-service` through HTTP Client to get the price, and asynchronous event based using Apache Kafka to publish information about newly created policies. In this service we also access RDBMS using JPA.

* **policy-search-service** - provides insurance policy search. \
This module listens for events from Kafka, converts received DTOs to “read model” (used later in search) and saves this in database (ElasticSearch). It also exposes REST endpoint for search policies.

* **pricing-service** - calculates price for selected insurance product. \
For each product a tariff should be defined. The tariff is a set of rules on the basis of which the price is calculated. MVEL language was used to define the rules. During the policy purchase process, the `policy-service` connects with this service to calculate a price. Price is calculated based on user’s answers for defined questions.

* **product-service** - simple insurance product catalog. \
Information about products are stored in MongoDB. Each product has code, name, image, description, cover list and question list (affect the price defined by the tariff). This module shows usage of reactive Mongo client.

* **auth-service** - JWT based authentication service, this services provides login functionality. \
Based on login and password users get authenticated and JWT token with their privileges is created and returned. This services shows built-in Micronaut support for JWT based security.

* **documents-service** - Service build with kotlin. Responsible for generating pdf document when new policy event is received.

* **chat-service** - Example WebSocket usage. Chat for salesman.

* **dashboard-service** - Business dashboards that presents our agents sales results. Dashboard service subscribes to events of selling policies and index sales data in ElasticSearch.
Then ElasticSearch aggregation framework is used to calculate sales stats like: total sales and number of policies per product per time period,
sales per agent in given time period and sales timeline. Sales stats are nicely visualized using ChartJS.

* **web-vue** - SPA application built with Vue.js and Bootstrap for Vue.

Each business microservice has also **-api module** (payment-service-api, policy-service-api etc.), where we defined commands, events, queries and operations. 

In the picture you can also see the component **internal-command-bus**. This component is used internally by microservices if we want to use a CQRS pattern inside (simple example in OfferController in policy-service).

## Building
This step requires **Java 14 (JDK), Maven** and **Yarn**.

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


## Running for development

### Prerequisites
* **docker**
* **docker-compose**

Windows users: make sure to set `core.autocrlf false` in git configuration before cloning this repository.

For Windows users, append below line ```C:\Windows\System32\drivers\etc\hosts```:
```
127.0.0.1 kafkaserver
```

For frontend app running, you must add file ```.env.local``` based on ```.env-example``` .
<p align="center">
    <img alt="Env" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/env.png" />
</p>

### Automated deployment

To run the whole system on local machine just type:
```
docker-run.sh
```
Make sure you've first built the microservices! Check [this](https://github.com/asc-lab/micronaut-microservices-poc#building).
This script will provision required infrastructure and start all services.
Setup is powered by docker-compose and configured via `docker-compose.yml` file.

Afterwards you need to add kafka cluster - either via web UI ([Kafka Manager](http://localhost:9000/) -> Cluster -> Add Cluster)
or using provided script:
```
kafka-create-cluster.sh
```

At this point system is ready to use: [http://localhost](http://localhost)

### Manual deployment

If you want to run services manually (eg. from IDE), you have to provision infrastructure with script from ```scripts``` folder:
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
* JSReport dashboard: ```http://localhost:5488/```


#### max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]

Check [this](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_set_vm_max_map_count_to_at_least_262144);

#### Add POLICY template to JsReport

<p align="center">
    <img alt="Zipkin" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/jsreport-add-template.png" />
</p>

1. Click "+".
2. Type name "POLICY" and content from file [policy.template](https://github.com/asc-lab/micronaut-microservices-poc/blob/master/documents-service/src/main/resources/policy.template).
3. Commit changes.

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

#### JSReport without our script
```
docker run -p 5488:5488 jsreport/jsreport
```

## OpenAPI/Swagger docs
**agent-portal-gateway** provides the API description.

You can go to URL: `[http://localhost:8081/swagger/lab-insurance-sales-portal-api-1.0.yml][http://localhost:8081/swagger/lab-insurance-sales-portal-api-1.0.yml]`

## Add new microservice

Create new microservice with [Micronaut CLI](http://guides.micronaut.io/micronaut-cli/guide/index.html):
```
mn create-app pl.altkom.asc.lab.[SERVICE-NAME]-service -b maven
```

This command generate project in Java and Maven as build tool.

## Examples

### Sample users

* admin / admin
* jimmy.solid / secret
* danny.solid / secret
* agent1 / agent1

### Chat
<p align="center">
    <img alt="Chat" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/chat_v2.gif" />
</p>

### Sales Dashboard
<p align="center">
    <img alt="Sales Dashboard" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/sales_dashboard.jpg" />
</p>

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

### Show document templates in JS Report
<p align="center">
    <img alt="JSReport" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/jsreport.png" />
</p>

## References
* [Building microservices with Micronaut - comprehensive guide](https://altkomsoftware.pl/en/blog/microservices-micronaut/)
* [Micronaut Docs](https://docs.micronaut.io/latest/guide/index.html)
* [Micronaut Workshop](https://alvarosanchez.github.io/micronaut-workshop/)
* [Building microservices with Micronaut (Part I)](https://mfarache.github.io/mfarache/Building-microservices-Micronoaut/)
* [Building microservices with Micronaut (Part II)](https://mfarache.github.io/mfarache/Traceability-microservices-Micronoaut/)
