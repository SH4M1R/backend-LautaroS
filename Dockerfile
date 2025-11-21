FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# **PERMISO para ejecutar mvnw**
RUN chmod +x mvnw

# Descargar dependencias offline
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Construir la app
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/restaurante-0.0.1-SNAPSHOT.jar"]

