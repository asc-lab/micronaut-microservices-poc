set -e
mvn clean package
docker build -t ascmn/chat-service:latest .
