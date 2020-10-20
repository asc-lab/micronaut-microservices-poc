FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/pricing-service*.jar pricing-service.jar
CMD java ${JAVA_OPTS} -jar pricing-service.jar