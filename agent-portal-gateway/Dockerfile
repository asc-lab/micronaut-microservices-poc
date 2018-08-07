FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/agent-portal-gateway*.jar agent-portal-gateway.jar
CMD java ${JAVA_OPTS} -jar agent-portal-gateway.jar
