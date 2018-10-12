set -e

BASE=`dirname $0`

(
    cd $BASE/..
    mvn clean install -f command-bus-api
    mvn clean install -f command-bus
    mvn clean install -f payment-service-api
    mvn clean install -f policy-service-api
    mvn clean install -f policy-search-service-api
    mvn clean install -f pricing-service-api
    mvn clean install -f product-service-api
    mvn clean install -f documents-service-api
)
