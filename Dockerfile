FROM gradle:6.7.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

EXPOSE 8001

RUN mkdir /app

COPY --from=build /home/gradle/src/bootstrap/build/libs/*.jar /app/nova-api.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/nova-api.jar"]