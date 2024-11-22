# Paso 1: Imagen base para la construcción (Maven con OpenJDK 8)
FROM maven:3.8.4-openjdk-8-slim AS build

# Paso 2: Establecer el directorio de trabajo
WORKDIR /app

# Paso 3: Copiar el archivo pom.xml (y otros archivos necesarios para resolver dependencias)
COPY pom.xml .

# Paso 4: Descargar las dependencias de Maven (para asegurarse de que no haya errores en las dependencias)
RUN mvn dependency:go-offline

# Paso 5: Copiar el código fuente de la aplicación
COPY src ./src

# Paso 6: Ejecutar Maven para empaquetar la aplicación, omitiendo las pruebas
RUN mvn clean package -DskipTests -Dmaven.test.failure.ignore=true

# Paso 7: Verificar los archivos generados (esto es opcional, útil para depuración)
RUN ls -l /app/

# Paso 8: Verificar si el archivo JAR tiene otro nombre o está en otro lugar
RUN find /app/ -name "*.jar"

# Paso 8: Imagen base para la ejecución de la aplicación (OpenJDK 8 con JRE)
FROM openjdk:8-jre-slim

# Paso 9: Establecer el directorio de trabajo
WORKDIR /app

# Paso 10: Copiar el archivo JAR empaquetado desde el contenedor de construcción
COPY --from=build /app/target/eaea.jar ./gestion_orquestas.jar
# Paso 11: Exponer el puerto en el que corre la aplicación (por defecto, Spring Boot usa el puerto 8080)
EXPOSE 8080

# Paso 12: Comando para ejecutar la aplicación
CMD ["java", "-jar", "gestion_orquestas.jar"]
