FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
COPY target/dashboard-service*.jar dashboard-service.jar
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar dashboard-service.jar