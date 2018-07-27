FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/consul-zipkin-example*.jar consul-zipkin-example.jar
CMD java ${JAVA_OPTS} -jar consul-zipkin-example.jar