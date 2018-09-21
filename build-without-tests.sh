mvn clean install -f command-bus-api
[ $? -eq 0 ] || exit 1

mvn clean install -f command-bus
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f documents-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f payment-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-search-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f pricing-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f product-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f auth-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f documents-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f payment-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-search-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f pricing-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f product-service -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

mvn clean install -f agent-portal-gateway -Dmaven.test.skip
[ $? -eq 0 ] || exit 1

yarn --cwd web-vue install
[ $? -eq 0 ] || exit 1
yarn --cwd web-vue run build
[ $? -eq 0 ] || exit 1
