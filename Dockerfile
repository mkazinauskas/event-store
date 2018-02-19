FROM openjdk:8-jdk-alpine
ADD src/main/resources/application.yml /app/application.yml
ADD build/libs/event-store-*.jar /app/event-store.jar
WORKDIR /app
ENTRYPOINT [ "sh", "-c", "java -jar event-store.jar" ]
