set -e
mvn clean package -DskipTests
docker build -t ascmn/pricing-service:latest .
