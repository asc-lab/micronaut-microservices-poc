FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/policy-search-service*.jar policy-search-service.jar
CMD java ${JAVA_OPTS} -jar policy-search-service.jar