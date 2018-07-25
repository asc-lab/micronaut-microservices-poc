FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/pricing-service*.jar pricing-service.jar
CMD java ${JAVA_OPTS} -jar pricing-service.jar