set -e
mvn clean package -DskipTests
docker build -t ascmn/payment-service:latest .
