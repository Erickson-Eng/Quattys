FROM amazoncorretto:11-al2-full
WORKDIR /app
COPY ./target/gateway-1.0-SNAPSHOT.jar gateway.jar
ARG EUREKA_SERVER
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "gateway.jar"]