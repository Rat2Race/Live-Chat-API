## 1. Use Amazon Corretto JDK 21 as the base image
#FROM amazoncorretto:21 AS builder
#
## 2. Install Gradle manually
#RUN yum install -y wget unzip && \
#    wget https://services.gradle.org/distributions/gradle-7.6-bin.zip && \
#    unzip gradle-7.6-bin.zip -d /opt && \
#    ln -s /opt/gradle-7.6/bin/gradle /usr/bin/gradle
#
## 3. Set the working directory
#WORKDIR /app
#
## 4. Copy Gradle wrapper and other necessary files for caching dependencies
#COPY gradlew /app/gradlew
#COPY gradle /app/gradle
#RUN chmod +x gradlew
#
## 5. Copy the application source code
#COPY . .
#
## 6. Build the application
#RUN ./gradlew build --no-daemon
#
## 7. Use a lightweight JDK image to run the application
#FROM amazoncorretto:21
#
## 8. Copy the built application from the builder stage
#COPY --from=builder /app/build/libs/*.jar /app/app.jar
#
## 9. Run the application
#CMD ["java", "-jar", "/app/app.jar"]
