FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} maps.jar
ENTRYPOINT ["java","-jar","/maps.jar"]