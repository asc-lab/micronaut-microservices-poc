set -e
mvn clean package
docker build -t ascmn/agent-portal-gateway:latest .
