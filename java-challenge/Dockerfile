#Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve -B
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

ARG JAR_FILE=target/java-challenge-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]