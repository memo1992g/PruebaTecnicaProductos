# PruebaTecnicaProductos
# Spring Boot Product Management Application

## Descripción

Esta es una aplicación desarrollada en Java utilizando Spring Boot que permite realizar operaciones CRUD en productos. Además, la aplicación incluye autenticación básica y estadísticas de uso.

## Requisitos

- Java 17 o superior
- Maven 3.6.0 o superior
- Docker (opcional, para ejecutar la aplicación en un contenedor)

## Tecnologías Utilizadas

- Spring Boot 3.3
- Spring Data JPA
- Spring Security
- H2 Database (Base de datos en memoria)
- JUnit 5 y Mockito (para pruebas unitarias)

## Características

- Crear un producto
- Actualizar un producto
- Eliminar un producto
- Listar todos los productos
- Listar un producto por ID
- Buscar productos por nombre
- Autenticación básica
- Generación de estadísticas de uso

## Instalación

### Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd pruebatecnica

Construir el proyecto
./mvnw clean install

Ejecutar la aplicación
./mvnw spring-boot:run



## Autenticación
La autenticación básica requiere un nombre de usuario y una contraseña. Por defecto, se utiliza:

Usuario: user
Contraseña: password

Endpoints

 ##Crear un producto

POST /api/products
Content-Type: application/json

{
  "name": "Nombre del Producto",
  "description": "Descripción del Producto",
  "price": 100.0
}

## Actualizar un producto
PUT /api/products/{id}
Content-Type: application/json

{
  "name": "Nombre Actualizado del Producto",
  "description": "Descripción Actualizada del Producto",
  "price": 150.0
}
## Eliminar productos

## DELETE /api/products/{id}  

## Listar todos los productos
GET /api/products

## Buscar productos por nombre
GET /api/products/search?name={name}

## Listar un producto por ID
GET /api/products/{id}

## Obtener estadísticas de uso

## Pruebas
./mvnw test

## Docker
Para construir y ejecutar la aplicación en un contenedor Docker, sigue estos pasos:

## Construir la imagen Docker
Asegúrate de que el archivo Dockerfile esté en el directorio raíz del proyecto.

Construye la imagen Docker:
docker build -t nombre-de-tu-imagen .
ejecuta el contenedor para probar
docker run -p 8080:8080 nombre-de-tu-imagen

## Despliegue en AWS EC2

Configura una instancia EC2: Sigue las instrucciones de AWS para lanzar una instancia EC2.
Transfiere el archivo JAR o la imagen Docker a la instancia: Utiliza SCP o S3 para transferir el archivo, en nuestro caso particular usamos s3 para la obtencion de la imagen .
Ejecuta la aplicación en la instancia: Ejecuta el archivo JAR con java -jar o inicia el contenedor Docker.
 para poder accesar a los servicios en aws solo hace falta agregar la ip publica y el puerto seguido de el llamado de los servicios 
http://52.14.121.176:8080/ 

cabe resaltar que esta api esta dockerizada por favor tomar en cuenta las configuraciones de contenedor con respecto al puerto 8080 tiene que estar habilitado 

## APARTADOS QUE NO SE LOGRARON TERMINAR 
se deja un archivo adjunto con el script dentro de la carpeta llamado ci-cd

## Explicación
name: Nombre del workflow.

on: Define cuándo se ejecuta el workflow (push y pull_request en la rama main).

jobs: Define los trabajos que se ejecutarán.

Build Job:

runs-on: Define el sistema operativo en el que se ejecutarán los pasos (ubuntu-latest).
steps: Los pasos del trabajo:
Checkout repository: Clona el repositorio.
Set up JDK 17: Configura JDK 17.
Build with Maven: Compila el proyecto con Maven.
Run tests: Ejecuta las pruebas.
Set up Docker Buildx: Configura Docker Buildx.
Log in to Docker Hub: Inicia sesión en Docker Hub.
Build and push Docker image: Construye y sube la imagen Docker a Docker Hub.
Deploy Job:

needs: Define que este trabajo depende del trabajo build.
runs-on: Define el sistema operativo en el que se ejecutarán los pasos (ubuntu-latest).
steps: Los pasos del trabajo:
Checkout repository: Clona el repositorio.
Set up SSH: Configura SSH para conectarse a la instancia EC2.
Deploy to EC2: Despliega la imagen Docker en la instancia EC2.
Variables Secretas
Configura las siguientes variables secretas en tu repositorio de GitHub:

DOCKER_USERNAME: Tu nombre de usuario de Docker Hub.
DOCKER_PASSWORD: Tu contraseña de Docker Hub.
SSH_PRIVATE_KEY: La clave privada SSH para conectarte a tu instancia EC2.
EC2_PUBLIC_IP: La dirección IP pública de tu instancia EC2.
Configurar las Variables Secretas
Ve a tu repositorio en GitHub.
Haz clic en Settings.
En el menú lateral, haz clic en Secrets and variables > Actions.
Haz clic en New repository secret para agregar cada una de las variables secretas mencionadas arriba


Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que te gustaría hacer.

Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Contacto
Si tienes alguna pregunta, por favor contacta al autor en [2gbguillermobermudez@gmail.com].





