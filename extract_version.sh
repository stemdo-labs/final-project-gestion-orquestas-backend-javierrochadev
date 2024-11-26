#!/bin/bash

# Archivo POM del cual extraer la versión
POM_FILE="pom.xml"

# Comprobamos si el archivo existe
if [[ ! -f "$POM_FILE" ]]; then
    echo "El archivo $POM_FILE no existe."
    exit 1
fi

# Extraer la versión dentro del bloque <parent> usando grep con sed para obtener el contenido de la etiqueta <version>
PARENT_VERSION=$(grep -A 3 "<parent>" "$POM_FILE" | grep "<version>" | sed 's/^[ \t]*<version>\(.*\)<\/version>/\1/')

# Comprobamos si se ha encontrado la versión
if [[ -z "$PARENT_VERSION" ]]; then
    echo "No se pudo encontrar la versión dentro del bloque <parent> en el archivo $POM_FILE."
    exit 1
fi

# Devolver solo la versión (sin ningún otro texto)
echo "$PARENT_VERSION"
