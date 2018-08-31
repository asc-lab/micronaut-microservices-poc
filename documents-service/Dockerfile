FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/documents-service*.jar documents-service.jar
CMD java ${JAVA_OPTS} -jar documents-service.jar