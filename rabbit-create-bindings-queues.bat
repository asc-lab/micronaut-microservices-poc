REM Create exchange micronaut-microservices-poc
curl -i -u guest:guest -H "content-type:application/json" ^
-XPUT -d"{""type"":""topic"",""durable"":true}" ^
http://localhost:15672/api/exchanges/%2f/micronaut-microservices-poc

REM Create topic policy-registered
curl -i -u guest:guest -H "content-type:application/json" ^
-XPUT -d"{""durable"":true}" ^
http://localhost:15672/api/queues/%2f/policy-registered

REM Create binding to policy-registered
curl -i -u guest:guest -H "content-type:application/json" ^
-XPOST -d"{""routing_key"":""policy-registered""}" ^
http://localhost:15672/api/bindings/%2f/e/micronaut-microservices-poc/q/policy-registered

REM Create topic policy-terminated
curl -i -u guest:guest -H "content-type:application/json" ^
-XPUT -d"{""durable"":true}" ^
http://localhost:15672/api/queues/%2f/policy-terminated

REM Create binding to policy-terminated
curl -i -u guest:guest -H "content-type:application/json" ^
-XPOST -d"{""routing_key"":""policy-registered""}" ^
http://localhost:15672/api/bindings/%2f/e/micronaut-microservices-poc/q/policy-registered
