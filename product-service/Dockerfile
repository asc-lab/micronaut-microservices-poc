FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
RUN apk --no-cache add curl
COPY target/product-service*.jar product-service.jar
CMD java ${JAVA_OPTS} -jar product-service.jar
