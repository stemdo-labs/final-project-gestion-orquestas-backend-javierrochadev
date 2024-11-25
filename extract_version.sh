#!/bin/bash

# Archivo POM del cual extraer y modificar el valor
POM_FILE="pom.xml"

# Comprobamos si el archivo existe
if [[ ! -f "$POM_FILE" ]]; then
    echo "El archivo $POM_FILE no existe."
    exit 1
fi

# Extraer el valor actual de <modelVersion>
MODEL_VERSION=$(grep "<modelVersion>" "$POM_FILE" | sed 's/^[ \t]*<modelVersion>\(.*\)<\/modelVersion>/\1/')

# Separar la versión en partes (Major, Middle, Minor)
IFS='.' read -r MAJOR MIDDLE MINOR <<< "$MODEL_VERSION"

# Incrementar la versión
if [[ $MINOR -lt 9 ]]; then
    MINOR=$((MINOR + 1))
else
    MINOR=0
    if [[ $MIDDLE -lt 9 ]]; then
        MIDDLE=$((MIDDLE + 1))
    else
        MIDDLE=0
        if [[ $MAJOR -lt 9 ]]; then
            MAJOR=$((MAJOR + 1))
        else
            echo "¡La versión ha alcanzado su límite máximo! 9.9.9"
            exit 1
        fi
    fi
fi

# Nueva versión
NEW_VERSION="$MAJOR.$MIDDLE.$MINOR"

# Mostrar la nueva versión
echo "$NEW_VERSION"
