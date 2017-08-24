FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/event-store-*.jar event-store.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /event-store.jar" ]