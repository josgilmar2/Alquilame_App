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
Esto es un proyecto sobre una app de alquiler de viviendas que se encuentran distribuidas por provincias. En ella, se podrán registrar distintos usuarios con un rol en concreto (PROPIETARIO O INQUILINO) que podrán hacer distintas cosas dentro de la aplicación. La principal diferencia será que el propietario podrá poner en venta las viviendas mientras que los inquilinos podrán visualizarlas y marcalas como favorita. 
Aquí se encuentra la parte del backend donde se ha desarrollado una API REST en lenguaje Java con el framework de **Spring**. Recordar que este es solamente el principio del proyecto por lo que se podrán realizar pocas funcionalidades, de las que destacamos el dar like a viviendas posteadas por otros usuarios.

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

Antes de ir con la ejecución, recuerda tener instalado tanto **Apache Maven** como el **IntelliJ IDEA**

Una vez entrado dentro del proyecto con el **IntelliJ IDEA** haz clic dentro del botón *Edit Configurations* y añade una configuración de tipo *Maven* y dentro del apartado *Run* escriba lo siguiente:

    spring-boot:run

Una vez añadida esta configuración, dándole al play ya estará ejecutándose el proyecto.

## Autor
Esta API REST ha sido realizada por:
* [José Luis Gil Martín](https://github.com/josgilmar2)