#!/bin/bash

# Archivo POM del cual extraer y modificar el valor
POM_FILE="pom.xml"

# Comprobamos si el archivo existe
if [[ ! -f "$POM_FILE" ]]; then
    echo "El archivo $POM_FILE no existe."
    exit 1
fi

# Verificar que se ha proporcionado una nueva versión como parámetro
if [[ -z "$1" ]]; then
    echo "Por favor, proporciona una nueva versión como parámetro."
    exit 1
fi

NEW_VERSION="$1"

# Actualizar el archivo POM con la nueva versión
sed -i "s/<modelVersion>.*<\/modelVersion>/<modelVersion>$NEW_VERSION<\/modelVersion>/" "$POM_FILE"

echo "El archivo POM se ha actualizado con la nueva versión: $NEW_VERSION"
