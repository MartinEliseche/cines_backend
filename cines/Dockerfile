# Etapa 1: Build con Maven y JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copio solo pom para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copio código fuente
COPY src ./src

# Build sin tests
RUN mvn clean package -DskipTests

# Etapa 2: Runtime con JRE 17 Alpine
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copio el jar construido del builder (el jar puede tener nombre variable)
COPY --from=builder /app/target/cines-*.jar app.jar

# Copio el entrypoint script y le doy permisos
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

# Exponemos el puerto 8080
EXPOSE 8080

# Usamos el entrypoint personalizado para esperar MySQL antes de arrancar
ENTRYPOINT ["./docker-entrypoint.sh"]
