# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
COPY . .
RUN mvn clean package -DskipTests



# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/quarkus-app /app/
EXPOSE 8080
CMD ["java", "-jar", "/app/quarkus-run.jar"]