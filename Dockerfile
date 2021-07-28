FROM openjdk:8-jdk-alpine

LABEL maintainer="Sunil M"


# Add the application's jar to the container
ARG jar=drool-db-rule-engine-0.0.1.jar
ENV jar=$jar
RUN mkdir -p /app/
WORKDIR /app/


ADD target/$jar .

EXPOSE 8080
EXPOSE 443

ENTRYPOINT java -jar /app/$jar