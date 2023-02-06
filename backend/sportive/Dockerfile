FROM amazoncorretto:11-al2-full
WORKDIR /app
COPY ./target/sportive-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]