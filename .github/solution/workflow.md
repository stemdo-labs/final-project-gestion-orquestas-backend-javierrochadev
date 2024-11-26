# Documentación del Workflow CI/CD Pipeline

Este flujo de trabajo de GitHub Actions automatiza el ciclo de vida de la integración continua (CI) y despliegue continuo (CD) de una aplicación. El pipeline realiza varias etapas, desde la extracción y actualización de versiones, hasta la construcción y despliegue de la aplicación en distintos entornos, incluyendo backend CI/CD.

## Activación del Workflow

El flujo de trabajo se activa de las siguientes maneras:

- **Push a la rama `main`**: Cada vez que se realiza un push a la rama principal.
- **workflow_dispatch**: Permite la activación manual del flujo de trabajo.

## Descripción de los Jobs

### Job: `deploy`

Este job se encarga de la extracción de la versión, su incremento y la actualización de los archivos correspondientes.

#### Pasos del Job

1. **Set up Node.js**:
   - Configura la versión de Node.js para el entorno de ejecución (versión `20`).

2. **Check out repository**:
   - Realiza un `checkout` del repositorio para acceder al código fuente.

3. **Set up script permissions**:
   - Asigna permisos de ejecución a los scripts `extract_version.sh` y `upload_version.sh`.

4. **Extract and increment version**:
   - Ejecuta el script `extract_version.sh` para obtener la versión actual y la incrementa. La nueva versión se guarda como una variable de entorno (`NEW_VERSION`) y se pasa a la salida del job para que esté disponible en los siguientes jobs.

5. **Upload version into file**:
   - Utiliza el script `upload_version.sh` para cargar la nueva versión extraída en los archivos correspondientes.

### Job: `call_backend_ci`

Este job se encarga de invocar un workflow de CI para el backend, pasándole la nueva versión de la aplicación.

- **Dependencia**: Este job depende de la finalización exitosa del job `deploy` (por medio de `needs: deploy`).
- **Acción**: Llama a un workflow de CI para el backend definido en el archivo `backend-CI.yaml` dentro de `.github/workflows`.
  
#### Parámetros del Job

- **`image_name`**: Nombre de la imagen Docker.
- **`image_tag`**: Etiqueta de la imagen, que es la nueva versión generada en el job anterior.

### Job: `call_backend_cd`

Este job se encarga de invocar un workflow de CD para el backend, utilizando la nueva versión y otros parámetros necesarios para el despliegue.

- **Dependencias**: Este job depende de la finalización exitosa de los jobs `deploy` y `call_backend_ci` (por medio de `needs: deploy` y `needs: call_backend_ci`).
- **Acción**: Llama a un workflow de CD para el backend definido en el archivo `backend-CD.yaml` dentro de `.github/workflows`.
  
#### Parámetros del Job

- **`image_name`**: Nombre de la imagen Docker.
- **`chart_name`**: Nombre del chart de Helm para el despliegue.
- **`image_tag`**: Etiqueta de la imagen, que es la nueva versión generada en el job `deploy`.

## Uso de Secrets

El flujo de trabajo utiliza varios secretos para la autenticación y configuración en servicios como Azure, ACR, Kubernetes y Harbor:

- **Para `deploy`**: No se usan secretos directamente.
- **Para `call_backend_ci` y `call_backend_cd`**: Los siguientes secretos son utilizados:
  - **Credenciales de Azure**: `ACR_NAME`, `ARM_CLIENT_ID`, `ARM_CLIENT_SECRET`, `ARM_TENANT_ID`.
  - **Kubernetes**: `RESOURCE_GROUP_NAME`, `AKS_NAME` .
  - **Base de datos**: `DB_NAME`, `DB_PASSWORD`, `DB_PORT`, `DB_USER`, `DB_HOST`.
  - **Harbor**: `HARBOR_USER`, `HARBOR_PASS`, `HARBOR_IP`.
  - **Contraseñas y datos de ACR**: `ACR_PASS`, `ACR_EMAIL`, `ACR_SECRET_NAME`.
  
Estos secretos permiten la conexión a los servicios necesarios, como Azure Container Registry (ACR), Azure Kubernetes Service (AKS), la base de datos y el registro Harbor.

---

Este flujo de trabajo es ideal para automatizar el ciclo completo de CI/CD, asegurando que cada commit y cada versión se construya, pruebe y despliegue correctamente a los entornos adecuados.
