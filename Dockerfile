FROM openjdk:8-jdk-alpine
VOLUME $HOME/.gradle /root/.gradle
ADD . /build 
RUN 	mkdir /app && \
	cd /build && \
	cp src/main/resources/application.yml /app/application.yml && \
	./gradlew build --parallel && \
	cp /build/build/libs/event-store-*.jar /app/event-store.jar && \
	rm -rf /build
WORKDIR /app
ENTRYPOINT [ "sh", "-c", "java -jar event-store.jar" ]
