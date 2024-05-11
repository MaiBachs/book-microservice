FROM openjdk:17

#FROM maven:alpine

 

# image layer

WORKDIR /app/Ebook

# Image layer: with the application

COPY ./EBook /app/Ebook
# Cài đặt Maven
#RUN apt-get update && \
#   apt-get install -y maven
#RUN mvn -v

#RUN mvn clean install -DskipTests

ENTRYPOINT ["java","-jar","/app/Ebook/target/EBook-0.0.1-SNAPSHOT.jar"]