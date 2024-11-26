#!/bin/bash

# Archivo POM donde se actualizará la versión
POM_FILE="pom.xml"

# Comprobamos si el archivo existe
if [[ ! -f "$POM_FILE" ]]; then
    echo "El archivo $POM_FILE no existe."
    exit 1
fi

# Verificar que se ha proporcionado una nueva versión como parámetro
if [[ -z "$1" ]]; then
    echo "Por favor, proporciona una nueva versión como parámetro (formato Major.Minor.Patch)."
    exit 1
fi

NEW_VERSION="$1"

# Validar que la nueva versión esté en formato Major.Minor.Patch
if [[ ! "$NEW_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
    echo "Error: La nueva versión '$NEW_VERSION' no es válida. Debe tener el formato Major.Minor.Patch (números separados por puntos)."
    exit 1
fi

# Separar la versión en partes (Major, Minor, Patch)
IFS='.' read -r MAJOR MINOR PATCH <<< "$NEW_VERSION"

# Incrementar la versión (lógica simple para incrementar el parche)
if [[ $PATCH -lt 9 ]]; then
    PATCH=$((PATCH + 1))
else
    PATCH=0
    if [[ $MINOR -lt 9 ]]; then
        MINOR=$((MINOR + 1))
    else
        MINOR=0
        if [[ $MAJOR -lt 9 ]]; then
            MAJOR=$((MAJOR + 1))
        else
            echo "¡La versión ha alcanzado su límite máximo! 9.9.9"
            exit 1
        fi
    fi
fi

# Nueva versión incrementada
NEW_VERSION="$MAJOR.$MINOR.$PATCH"

# Actualizar el archivo POM con la nueva versión (en el bloque <parent>)
sed -i "s|<version>.*</version>|<version>$NEW_VERSION</version>|" "$POM_FILE"

echo "El archivo POM se ha actualizado con la nueva versión: $NEW_VERSION"
