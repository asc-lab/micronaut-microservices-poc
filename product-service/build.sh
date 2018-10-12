set -e
mvn clean package
docker build -t ascmn/product-service:latest .
