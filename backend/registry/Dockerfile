FROM amazoncorretto:11-al2-full
WORKDIR /app
COPY ./target/registry-1.0-SNAPSHOT.jar registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "registry.jar"]