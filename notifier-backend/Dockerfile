#
# Build stage
#
FROM maven:3-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
# TODO create user to avoid running by root
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /home/app/target/*.jar /usr/local/lib/runner.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/runner.jar"]
