FROM openjdk:17

#FROM maven:alpine

 

# image layer

WORKDIR /app/DiscoveryServer

# Image layer: with the application

COPY ./DiscoveryServer /app/DiscoveryServer
# Cài đặt Maven
#RUN apt-get update && \
#    apt-get install -y maven
#RUN mvn -v

#RUN mvn clean install -DskipTests

ENTRYPOINT ["java","-jar","/app/DiscoveryServer/target/DiscoveryServer-0.0.1-SNAPSHOT.jar"]