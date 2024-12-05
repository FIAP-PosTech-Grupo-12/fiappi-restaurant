FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /usr/src/app/target/fiappi-0.0.1-SNAPSHOT.jar /app/fiappi-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/app/fiappi-0.0.1-SNAPSHOT.jar"]