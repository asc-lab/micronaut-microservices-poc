mvn clean install -f policy-service-api
mvn clean install -f pricing-service-api
mvn clean install -f product-service-api

(
  cd auth-service
  mvn clean install 
  docker build -t mn-auth-service .
)


(
  cd payment-service
  mvn clean install
  docker build -t mn-payment-service .
)


(
  cd policy-service
  mvn clean install -Dmaven.test.skip
  docker build -t mn-policy-service .
)


(
  cd pricing-service
  mvn clean install -Dmaven.test.skip
  docker build -t mn-pricing-service .
)


(
  cd product-service
  mvn clean install 
  docker build -t mn-product-service .
)


(
  cd agent-portal-gateway
  mvn clean install
  docker build -t mn-agent-portal-gateway .
)

(
  cd web-vue
  yarn install
  yarn run build
  docker build -t mn-web-vue .
)

