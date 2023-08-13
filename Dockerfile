# Start with a base image containing Java runtime
FROM openjdk:8

# Make port 8080 available to the world outside this container
EXPOSE 8091

ADD target/boot-sonar-docker.jar boot-sonar-docker

# Run the jar file 
ENTRYPOINT ["java","-jar","boot-sonar-docker.jar"]