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

mvn clean install -f dashboard-service-api
[ $? -eq 0 ] || exit 1

mvn clean install -f auth-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f payment-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f policy-search-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f pricing-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f product-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f documents-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f dashboard-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f chat-service -DskipTests
[ $? -eq 0 ] || exit 1

mvn clean install -f agent-portal-gateway -DskipTests
[ $? -eq 0 ] || exit 1
