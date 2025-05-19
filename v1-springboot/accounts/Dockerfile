# Start with base image containing Java runtime
FROM openjdk:21-jdk-slim

# Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application JAR to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts.jar"]

# docker build . -t tann0308/accounts:s4
# docker inspect image 9710c98f7473
# docker run -d -p 8080:8080 --name accounts tann0308/accounts:s4