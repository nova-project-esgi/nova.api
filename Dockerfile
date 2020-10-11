FROM openjdk:11-jre-slim

ENV APPLICATION_USER ktor
RUN adduser --disabled-password  $APPLICATION_USER; mkdir /app; chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

COPY ./app/build/libs/com.esgi.nova-0.0.1.jar /app/com.esgi.nova-0.0.1.jar
WORKDIR /app

CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "com.esgi.nova-0.0.1.jar"]
