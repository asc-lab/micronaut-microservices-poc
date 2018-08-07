FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
COPY target/product-service*.jar product-service.jar
CMD java ${JAVA_OPTS} -jar product-service.jar
