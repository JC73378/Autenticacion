# Build stage: compila la app y empaqueta el JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Runtime stage: usa una imagen ligera solo con el JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Railway inyecta PORT; expone 8080 por convención y el entrypoint la leerá
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
