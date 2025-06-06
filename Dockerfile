# Base image with JDK and Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -Pprod -DskipTests

# Final runtime image
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy JAR from build stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${TOMCAT_PORT}

# Run app
CMD ["java", "-jar", "app.jar"]