FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
COPY target/auth-service*.jar auth-service.jar
CMD java ${JAVA_OPTS} -jar auth-service.jar
