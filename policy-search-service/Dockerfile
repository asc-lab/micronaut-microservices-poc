FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/policy-search-service*.jar policy-search-service.jar
CMD java ${JAVA_OPTS} -jar policy-search-service.jar