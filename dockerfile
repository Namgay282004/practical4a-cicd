# Use a Java base image - Eclipse Temurin is the official OpenJDK distribution
FROM eclipse-temurin:17-jre-alpine

# https://medium.com/@skywalkerhunter/aws-docker-deploy-spring-boot-fe05a00191d9
# added on 31st Oct
LABEL maintainer="Darryl Ng <darryl1975@hotmail.com>"
LABEL description="Dockerfile for deploying to Beanstalk needs dockerrun.aws.json"

# Create a non-root user for security
RUN addgroup -g 1001 -S appuser && \
    adduser -u 1001 -S appuser -G appuser

# Set the working directory to /app
WORKDIR /app

# Copy the Spring Boot application JAR file into the Docker image
COPY target/cicd-demo-0.0.1-SNAPSHOT.jar /app/cicd-demo-0.0.1-SNAPSHOT.jar

# Change ownership to non-root user
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose the port that the Spring Boot application is listening on
EXPOSE 3000

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "cicd-demo-0.0.1-SNAPSHOT.jar"]