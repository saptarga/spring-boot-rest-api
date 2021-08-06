FROM amazoncorretto:11-alpine

RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p /app
RUN chown spring:spring /app

USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar
WORKDIR /app

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080/tcp