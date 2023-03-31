FROM maven:3.6.3-openjdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:17-alpine
COPY --from=build /home/app/target/BootstrapNode-1.0-SNAPSHOT.jar /usr/local/lib/nosqlsystem.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/nosqlsystem.jar"]