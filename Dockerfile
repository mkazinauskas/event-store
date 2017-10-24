FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/event-store-*.jar event-store.jar
ADD src/main/resources/application.yml application.yml
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /event-store.jar --spring.profiles.active=container" ]