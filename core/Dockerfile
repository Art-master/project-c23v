FROM openjdk:8-jdk-alpine
ARG JAR_FILE=webapps/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]