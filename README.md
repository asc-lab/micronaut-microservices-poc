# ASCLAB Micronaut PoC

Create new microservice with [Micronaut CLI](http://guides.micronaut.io/micronaut-cli/guide/index.html):
```
mn create-app pl.altkom.asc.lab.[SERVICE-NAME]-service -f spock -b maven
```

This command generate project with Spock test and Maven as build tool.

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

## Prerequisites

### Consul
```
docker run -p 8500:8500 consulsho
```
Open dashboard:
```
http://localhost:8500
```
### Zipkin
```
docker run -d -p 9411:9411 openzipkin/zipkin
```
Open dashboard:
```
http://localhost:9411/zipkin/
```

### Kafka
Setup Kafka on Windows with [this instruction](https://zablo.net/blog/post/setup-apache-kafka-in-docker-on-windows).
Folder [kafka-docker] contains the script copied from the above instruction.

Open dashboard:
```
http://localhost:9000/
```

## Examples

Example JSON for `/policies` POST:
```
{
	"policyVersion": {
		"policyNumber": "P1",
		"productCode": "ABO_GOLD",
		"policyHolder": {
			"firstName": "Jan",
			"lastName": "Bak",
			"pesel": "11111111116"
		},
		"accountNumber": "901291092012910",
		"versionNumber": 1,
		"covers": [{
				"code": "C1",
				"services": [{
						"code": "S1",
						"coPayment": {
							"percent": 10,
							"amount": null
						},
						"limit": {
							"periodTypeCode": "POLICY_YEAR",
							"maxQuantity": 10,
							"maxAmount": null
						}
					}
				]
			}
		]
	}
}
```

## Dashboard examples

### Tracing requests with Zipkin
<p align="center">
    <img alt="Zipkin" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/zipkin.png" />
</p>

## Show topics on Kafka
<p align="center">
    <img alt="Kafka" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/kafka.png" />
</p>

## Show services in Consul
<p align="center">
    <img alt="Consul" src="https://raw.githubusercontent.com/asc-lab/micronaut-microservices-poc/master/readme-images/consul.png" />
</p>


## References
* [Micronaut Workshop](https://alvarosanchez.github.io/micronaut-workshop/)
* [Building microservices with Micronaut (Part I)](https://mfarache.github.io/mfarache/Building-microservices-Micronoaut/)
* [Building microservices with Micronaut (Part II)](https://mfarache.github.io/mfarache/Traceability-microservices-Micronoaut/)
