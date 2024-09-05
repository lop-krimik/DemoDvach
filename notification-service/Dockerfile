FROM eclipse-temurin:21
WORKDIR /app
COPY /target/notification-service-0.0.1-SNAPSHOT.jar /app/notification.jar
ENTRYPOINT ["java","-jar","notification.jar"]
EXPOSE 8083