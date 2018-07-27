# ASCLAB Micronaut PoC

Create new microservice with [Micronaut CLI](http://guides.micronaut.io/micronaut-cli/guide/index.html):
```
mn create-app pl.altkom.asc.lab.[SERVICE-NAME]-service -f spock -b maven
```

This command generate project with Spock test and Maven as build tool.

## Run selected services

### Consul
```
docker run -p 8500:8500 consul
```
### Zipkin
```
docker run -d -p 9411:9411 openzipkin/zipkin
```
### Kafka
Go to [kafka-example](kafka-docker/README.md).

### Mongo
```
docker run -d --name=dev-mongo -p 27017:27017 mongo
```

## Dashboards

### Consul
```
http://localhost:8500
```
### Zipkin
```
http://localhost:9411/zipkin/
```
### Kafka
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