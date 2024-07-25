FROM openjdk:17 as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/bank-api-1.jar /app/bank-api-1.jar
COPY opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
COPY dev.properties /app/dev.properties
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.javaagent.configuration.file=/app/dev.properties", "-Dotel.resource.attributes=service.name=java-bank-api", "-jar", "bank-api-1.jar"]
