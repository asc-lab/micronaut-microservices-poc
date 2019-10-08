start /b java -jar auth-service/target/auth-service-0.1.jar
start /b java -jar payment-service/target/payment-service-1.0.jar
start /b java -jar policy-service/target/policy-service-1.0.jar
start /b java -jar policy-search-service/target/policy-search-service-1.0.jar
start /b java -jar pricing-service/target/pricing-service-1.0.jar
start /b java -jar product-service/target/product-service-1.0.jar
start /b java -jar dashboard-service/target/dashboard-service-1.0.jar
start /b java -jar agent-portal-gateway/target/agent-portal-gateway-1.0.jar

start /b yarn --cwd web-vue run serve