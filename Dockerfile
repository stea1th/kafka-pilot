FROM java:8-jdk-alpine
COPY ./target/kafka-pilot-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "kafka-pilot-0.0.1-SNAPSHOT.war"]
