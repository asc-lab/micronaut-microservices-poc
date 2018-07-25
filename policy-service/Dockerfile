FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/policy-service*.jar policy-service.jar
CMD java ${JAVA_OPTS} -jar policy-service.jar