FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/payment-service*.jar payment-service.jar
CMD java ${JAVA_OPTS} -jar payment-service.jar