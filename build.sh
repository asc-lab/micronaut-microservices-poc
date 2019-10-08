mvn clean install -f command-bus-api
[ $? -eq 0 ] || exit 1

mvn clean install -f command-bus
[ $? -eq 0 ] || exit 1

mvn clean install -f payment-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-search-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f pricing-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f product-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f documents-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f dashboard-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f auth-service
[ $? -eq 0 ] || exit 1

mvn clean install -f payment-service
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-service
[ $? -eq 0 ] || exit 1

mvn clean install -f documents-service
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-search-service
[ $? -eq 0 ] || exit 1

mvn clean install -f pricing-service
[ $? -eq 0 ] || exit 1

mvn clean install -f product-service
[ $? -eq 0 ] || exit 1

mvn clean install -f dashboard-service
[ $? -eq 0 ] || exit 1

mvn clean install -f agent-portal-gateway
[ $? -eq 0 ] || exit 1

mvn clean install -f chat-service
[ $? -eq 0 ] || exit 1

yarn --cwd web-vue install
[ $? -eq 0 ] || exit 1
yarn --cwd web-vue run build
[ $? -eq 0 ] || exit 1