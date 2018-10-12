set -e
mvn clean package -DskipTests
docker build -t ascmn/policy-search-service:latest .
