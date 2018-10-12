set -e
mvn clean package -DskipTests
docker build -t ascmn/policy-service:latest .
