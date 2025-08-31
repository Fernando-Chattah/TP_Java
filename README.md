# Tp Integrador - Proyecto Spring Boot con MongoDB

## Descripción
Proyecto Spring Boot que demuestra cómo guardar la misma entidad Product en dos proveedores NoSQL diferentes:
- **MongoDB Atlas** (usando Spring Data MongoDB)


Este proyecto es ideal para demostrar las diferencias entre ambos proveedores NoSQL en una clase en vivo.

## Tecnologías Utilizadas
- **Spring Boot 3.5.4**
- **Java 17**
- **Maven**
- **Spring Boot Web**
- **Spring Data MongoDB**
- **Spring Boot Validation**
- **Lombok**
- **Spring Boot DevTools**

## Configuración del Proyecto

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior
- **MongoDB Atlas** - Clúster configurado con URI de conexión

### Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd TpIntegradpr
   ```

2. . **Compilar el proyecto:**
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

5. **Verificar que la aplicación esté funcionando:**
   ```bash
   curl http://localhost:8080/hello
   ```

## Endpoints Disponibles

### API REST
- `GET /hello` - Retorna "HELLO"
- `POST /products` - Crea tarea en MongoDB Atlas
- `GET /products/{id}` - Obtiene tarea de MongoDB por ID


## Arquitectura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │    │     Service     │    │   Repositories  │
│                 │    │                 │    │                 │
│ProductController│───▶│ ProductService  │───▶│ MongoProductRepo│
│                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │     Domain      │    │   NoSQL Stores  │
│                 │    │                 │    │                 │
│ ProductRequest  │    │     Product     │    │   MongoDB Atlas │
│ ProductResponse │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```