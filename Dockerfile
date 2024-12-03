# Etapa 1: Construcción
FROM maven:3.8.8-eclipse-temurin-17 as build-stage

# Configurar directorio de trabajo
WORKDIR /app
 
# Copiar archivos de configuración y dependencias para aprovechar caché
COPY pom.xml ./
COPY src ./src
 
# Construir el proyecto y empaquetarlo como JAR
RUN mvn clean package -DskipTests
 
# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre as runtime-stage
 
# Directorio de la aplicación en el contenedor
WORKDIR /app
 
# Copiar solo el JAR desde la etapa de construcción
COPY --from=build-stage /app/target/*.jar app.jar
 
# Exponer el puerto por donde se sirve la aplicación
EXPOSE 8080

# Comando de inicio
CMD ["java", "-jar", "app.jar"]
