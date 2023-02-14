FROM amazoncorretto:11-al2-full
WORKDIR /app
COPY ./target/manager-1.0-SNAPSHOT.jar manager.jar
ARG EUREKA_SERVER
ENTRYPOINT ["java", "-jar", "manager.jar"]