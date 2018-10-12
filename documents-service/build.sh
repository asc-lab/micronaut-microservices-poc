set -e
mvn clean package
docker build -t ascmn/documents-service:latest .
