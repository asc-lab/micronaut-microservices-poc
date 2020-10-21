FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/dashboard-service-1.0.jar dashboard-service.jar
CMD java ${JAVA_OPTS} -jar dashboard-service.jar