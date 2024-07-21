FROM ubuntu:latest

WORKDIR /app

RUN apt-get update && apt-get install -y maven openjdk-17-jdk

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
RUN export JAVA_HOME

COPY Banking-System-Back/pom.xml .
COPY Banking-System-Back/src ./src

RUN mvn dependency:resolve
RUN mvn clean package

COPY /app/Banking-System-Back/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]