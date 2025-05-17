FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

# Copiar arquivos de dependências e build primeiro (otimização de cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Construir o jar sem testes
RUN ./mvnw install -DskipTests

# Imagem de runtime
FROM eclipse-temurin:24-jre-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]