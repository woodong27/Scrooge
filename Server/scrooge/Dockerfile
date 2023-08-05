# 빌드 단계: Gradle 설치 및 애플리케이션 빌드
FROM openjdk:8 AS build

# Base image with OpenJDK 8
FROM openjdk:8-jdk

# Set working directory
WORKDIR /app

# Copy Spring application jar into the container
COPY ./build/libs/scrooge-0.0.1-SNAPSHOT.jar .

# Expose Spring port
EXPOSE 8081

# Command to run the Spring application
CMD ["java", "-jar", "scrooge-0.0.1-SNAPSHOT.jar"]
