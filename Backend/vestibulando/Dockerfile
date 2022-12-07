#Build stage
FROM maven:3.8.1-openjdk-17-slim AS build
ARG MYSQL_URL=jdbc:mysql://tcp-mo1.mogenius.io:19062/vestibulando

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /home/app/target/vestibulando-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
