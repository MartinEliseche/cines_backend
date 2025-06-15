# Etapa 1: Build del proyecto con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiamos pom.xml y resolvemos dependencias primero para aprovechar cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto y generamos el .jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana para producción
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiamos el .jar generado
COPY --from=builder /app/target/app.jar app.jar

# Copiamos el entrypoint que espera a MySQL
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

# Exponemos el puerto del backend
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["./docker-entrypoint.sh"]
