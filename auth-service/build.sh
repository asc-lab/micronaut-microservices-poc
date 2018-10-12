set -e
mvn clean package
docker build -t ascmn/auth-service:latest .