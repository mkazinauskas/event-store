FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/event-store-*.jar event-store.jar
ENV JAVA_OPTS="-DPOSTGRES_USER=$POSTGRES_USER"
ENTRYPOINT exec java $JAVA_OPTS -jar /event-store.jar