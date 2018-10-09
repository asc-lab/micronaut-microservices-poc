FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
COPY target/chat-service*.jar chat-service.jar
CMD java ${JAVA_OPTS} -jar chat-service.jar
