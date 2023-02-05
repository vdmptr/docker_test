FROM openjdk:17
MAINTAINER vdm.user

WORKDIR /
COPY target/user-service-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8081

