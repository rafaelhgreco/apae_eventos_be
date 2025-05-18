FROM openjdk:17-jdk-slim

# Instalar o supervisor (para gerenciar múltiplos processos)
RUN apt-get update && apt-get install -y supervisor postgresql postgresql-contrib wget

# Configurar o PostgreSQL
USER postgres
RUN /etc/init.d/postgresql start && \
    createdb -O postgres panorama
USER root

# Configurar o Supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Copiar o JAR do Spring Boot
WORKDIR /app
COPY target/*.jar app.jar

# Expor portas
EXPOSE 8080 5432

# Iniciar o Supervisor (que controlará PostgreSQL e Spring Boot)
CMD ["/usr/bin/supervisord"]