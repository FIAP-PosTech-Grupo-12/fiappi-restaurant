FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /usr/src/app/target/fiappi-restaurant-0.0.2-SNAPSHOT.jar /app/fiappi-restaurant-0.0.2-SNAPSHOT.jar
EXPOSE 8081
CMD ["java","-jar","/app/fiappi-restaurant-0.0.2-SNAPSHOT.jar"]