FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/*.jar app.jar
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://spring-demo-mongo/users", "-jar", "/app.jar"]
