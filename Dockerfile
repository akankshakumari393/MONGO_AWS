FROM openjdk:8u111-jdk-alpine

RUN apk update && apk add bash

RUN mkdir -p /timesheet
ENV PROJECT_HOME /timesheet

WORKDIR $PROJECT_HOME



ADD /target/*.jar ./timesheet_app.jar
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo-service/users", "-jar", "./timesheet_app.jar"]
