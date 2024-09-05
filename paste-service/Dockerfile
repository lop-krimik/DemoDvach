FROM eclipse-temurin:21
WORKDIR /app
COPY /target/paste-service-0.0.1-SNAPSHOT.jar /app/paste.jar
ENTRYPOINT ["java","-jar","paste.jar"]
EXPOSE 8084
