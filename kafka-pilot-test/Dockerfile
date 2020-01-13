FROM java:8-jdk-alpine
COPY ./target/kafka-pilot-test-0.0.1-SNAPSHOT.war /usr/app/client/
WORKDIR /usr/app/client
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "kafka-pilot-test-0.0.1-SNAPSHOT.war"]
