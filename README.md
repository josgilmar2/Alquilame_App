# Alquilame_App
## API REST con el framework de Spring

<p align="center">
  <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"><img src="https://img.shields.io/badge/jdk-v17.0.4.1-blue" alt="Versión java" /></a>
  <a href="https://maven.apache.org/download.cgi"><img src="https://img.shields.io/badge/apache--maven-v3.8.6-blue" alt="Versión maven" /></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/spring--boot-v2.7.8-blue" alt="Versión spring-boot" /></a>
  <img src="https://img.shields.io/badge/release%20date-february-yellowgreen" alt="Lanzamiento del proyecto" />
  <img src="https://img.shields.io/badge/license-MIT-brightgreen" alt="Licencia" />
</p>

## Documentación

:point_right: [Dirección Swagger-ui](http://localhost:8080/swagger-ui/index.html#/)

:point_right: Se incluye también una colección de Postman para probar los endpoints

## Descripción
Esto es un proyecto sobre una app de alquiler de viviendas que se encuentran distribuidas por provincias. En ella, se podrán registrar distintos usuarios con un rol en concreto (PROPIETARIO O INQUILINO) que podrán realizar distintas funciones dentro de la aplicación. La principal diferencia será que el propietario podrá poner en venta las viviendas mientras que los inquilinos podrán visualizarlas y marcalas como favorita. Recordar que está es la primera versión del proyecto, por lo que se podrán realizar pocas funcionalidades, de las que destacamos el dar like a viviendas posteadas por otros usuarios. A lo largo de estos meses iré agregando más funcionalidades coo la de gestionar el alquiler de la vivienda.
Aquí se encuentra la parte del backend donde se ha desarrollado una API REST en lenguaje Java con el framework de **Spring**.
## Tecnologías utilizadas
Para realizar esta API REST hemos utilizado:
1. [Spring Boot 2.7.8](https://spring.io/)
2. [Apache Maven 3.8.6](https://maven.apache.org/)
3. [IntelliJ Idea](https://www.jetbrains.com/idea/)
4. [Postman](https://www.postman.com/)
5. [Swagger](https://swagger.io/)

## Arranque
Realiza un *git clone* del siguiente repositorio: *https://github.com/josgilmar2/Alquilame_App*

```console
git clone https://github.com/josgilmar2/Alquilame_App.git
```

Dirígete hasta la carpeta:

> cd ./Alquilame/

Antes de ir con la ejecución, recuerda tener instalado tanto **Apache Maven** como **IntelliJ IDEA** y **Docker**. Además el sistema gestor de base de datos utilizado en producción es PostgreSQL, por lo que necesitamos realizar lo siguientes pasos:
1. Una vez dentro de la carperta alquílame vemos que hay que un archivo llamado *docker-compose.yml* donde dentro se encuentra la imagen de **PostgreSQL** como la de **PgAdmin** para luego poder comprobar que todo se está realizando correctamente. Para ejecutar este contendor tendremos que poner por consola:
```console
docker-compose up -d
```

2. Una vez ejecutado el comando anterior, entramos dentro del proyecto con el **IntelliJ IDEA**. Haz clic dentro del botón *Edit Configurations* y añade una configuración de tipo *Maven* y dentro del apartado *Run* escriba lo siguiente:

    spring-boot:run

Una vez añadida esta configuración, dándole al play ya estará ejecutándose el proyecto.

3. Para comprobar que los datos importados dentro del archivo *import.sql* se están guardando correctamente dentro de la base de datos podemos escribir en nuestro navegador favorito la siguiente dirección:

    http://localhost:5050/

Entraremos dentro del PgAdmin. Para acceder escribiremos tanto el email como la contraseña que se encuentran escritas dentro del fichero *docker-compose.yml*. Una vez dentro haremos clic en *Servers* y registraremos uno nuevo. Podréis llamarle como queráis pero dentro del apartado *Connection* escribiremos lo siguiente en los distintos apartados:
* Hostname / Address: **db**
* Port: **5432**
* Username: **user**
* Password: **password**

## Autor
Esta API REST ha sido realizada por:
* [José Luis Gil Martín](https://github.com/josgilmar2)