#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY ./target/*.jar /app/runner.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/runner.jar
