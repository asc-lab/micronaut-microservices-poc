FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
#jre-14.0.2_12-alpine doesn't have curl in our packages
#RUN apk --no-cache add curl
COPY target/auth-service*.jar auth-service.jar
CMD java ${JAVA_OPTS} -jar --enable-preview auth-service.jar
