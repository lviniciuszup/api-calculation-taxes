FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/tax-calculation-api-0.0.1-SNAPSHOT.jar app.jar

COPY .env .env

ENTRYPOINT ["java", "-jar", "app.jar"]