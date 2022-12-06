#Build stage
FROM maven:3.8.1-openjdk-17-slim AS build
ADD . /home/app
RUN ls /home/app -alh
RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /home/app/target/vestibulando-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
