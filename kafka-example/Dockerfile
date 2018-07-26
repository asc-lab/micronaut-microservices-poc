FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/kafka-example*.jar kafka-example.jar
CMD java ${JAVA_OPTS} -jar kafka-example.jar