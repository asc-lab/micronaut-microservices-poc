FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/agent-portal-gateway*.jar agent-portal-gateway.jar
CMD java ${JAVA_OPTS} -jar agent-portal-gateway.jar
