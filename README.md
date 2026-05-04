# InnovaTech Solutions - Arquitectura de Microservicios

Este repositorio contiene el backend para el sistema de "Gestión de Recursos" de InnovaTech Solutions. La solución está construida bajo una arquitectura de microservicios utilizando Spring Boot, permitiendo escalabilidad y separación de responsabilidades.

**Autor:** Ignacio Alexander Pérez Silva

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.4.x**
- **Spring Data JPA**
- **H2 Database** (Base de datos relacional en memoria)
- **Spring Cloud OpenFeign** (Comunicación síncrona entre microservicios)
- **Maven Wrapper** (`mvnw`)

## 🏗️ Arquitectura del Sistema

El sistema se compone de dos microservicios principales que interactúan entre sí:

1. **Project Service (Puerto 8023):** Encargado de la gestión y persistencia de los proyectos de la empresa.
2. **Resource Service (Puerto 8024):** Encargado de asignar recursos (humanos, materiales, financieros) a los proyectos existentes. Utiliza un cliente declarativo (`FeignClient`) para validar la existencia del proyecto en el puerto 8023 antes de realizar una asignación.

---

## 🚀 Instrucciones de Despliegue Local (Paso a Paso)

Siga estas instrucciones para clonar, compilar y ejecutar la aplicación en un entorno local.

### 1. Clonar el Repositorio

Abra una terminal y ejecute el siguiente comando para descargar el código fuente:

```bash
git clone https://github.com/TheolaClan465/JAVA.git
cd java
```

### 2. En la terminal

Dentro de la terminal, debe acceder a project-service y ejecutar el siguiente comando:

```bash
cd project-service
.\mvnw clean package
```

### 3. Ejecutar los servicios

Abra 2 terminales en el proyecto y abra las 2 carpetas:

```bash
cd project-service
.\mvnw spring-boot:run
```

```bash
cd resource-service
.\mvnw spring-boot:run
```

### 4. Pruebas en Postman

Ahora abra Postman y haga consultas en los siguientes links:

http://localhost:8023/api/projects

http://localhost:8024/api/resources

En el controller están todas las direcciones para los metodos CRUD
