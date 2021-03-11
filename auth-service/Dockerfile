FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/auth-service*.jar auth-service.jar
CMD java ${JAVA_OPTS} -jar --enable-preview auth-service.jar
