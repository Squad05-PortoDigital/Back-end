# Etapa 1: Construir o JAR
FROM maven:3.8.7-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia o pom.xml e outros arquivos necessários para resolver as dependências
COPY pom.xml ./
COPY src ./src

# Resolve as dependências e compila o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem leve para rodar o JAR
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expondo a porta
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]