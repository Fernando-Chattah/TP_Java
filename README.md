# TpIntegradpr - Proyecto Spring Boot con MongoDB y Firebase

## Descripción
Proyecto Spring Boot que demuestra cómo guardar la misma entidad Task en dos proveedores NoSQL diferentes:
- **MongoDB Atlas** (usando Spring Data MongoDB)
- **Firebase Firestore** (usando Firebase Admin SDK)

Este proyecto es ideal para demostrar las diferencias entre ambos proveedores NoSQL en una clase en vivo.

## Tecnologías Utilizadas
- **Spring Boot 3.5.4**
- **Java 17**
- **Maven**
- **Spring Boot Web**
- **Spring Data MongoDB**
- **Firebase Admin SDK 9.2.0**
- **Spring Boot Validation**
- **Lombok**
- **Spring Boot DevTools**

## Configuración del Proyecto

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior
- **MongoDB Atlas** - Clúster configurado con URI de conexión
- **Firebase Project** - Proyecto configurado con credenciales de Admin SDK

### Configuración

#### 1. Configurar MongoDB Atlas
1. Crear un clúster en MongoDB Atlas
2. Obtener la URI de conexión
3. Actualizar `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/database
   ```

#### 2. Configurar Firebase Firestore
1. Crear un proyecto en Firebase Console
2. Habilitar Firestore Database
3. Generar credenciales de Admin SDK:
   - Ir a Project Settings > Service Accounts
   - Click en "Generate new private key"
   - Descargar el archivo JSON
4. Colocar el archivo en `src/main/resources/firebase-adminsdk.json`
5. Actualizar `application.properties` si es necesario:
   ```properties
   firebase.credentials=classpath:firebase-adminsdk.json
   firebase.database-url=https://tu-proyecto.firebaseio.com
   ```

### Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd TpIntegradpr
   ```

2. **Configurar credenciales:**
   - Copiar `firebase-adminsdk.json.example` a `firebase-adminsdk.json`
   - Actualizar con tus credenciales reales
   - Actualizar URI de MongoDB en `application.properties`

3. **Compilar el proyecto:**
   ```bash
   mvn clean compile
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
- `POST /tasks/mongo` - Crea tarea en MongoDB Atlas
- `POST /tasks/firebase` - Crea tarea en Firebase Firestore
- `GET /tasks/mongo/{id}` - Obtiene tarea de MongoDB por ID
- `GET /tasks/firebase/{id}` - Obtiene tarea de Firebase por ID

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/bbva/TpIntegradpr/
│   │       ├── TpIntegradprApplication.java
│   │       ├── domain/
│   │       │   └── Task.java                    # Entidad de dominio
│   │       ├── web/
│   │       │   ├── TaskRequest.java             # DTO para peticiones
│   │       │   └── TaskResponse.java            # DTO para respuestas
│   │       ├── adapters/
│   │       │   ├── mongo/
│   │       │   │   ├── ProductDocument.java        # Documento MongoDB
│   │       │   │   ├── MongoTaskRepository.java # Repositorio MongoDB
│   │       │   │   └── MongoTaskMapper.java     # Mapper MongoDB
│   │       │   └── firebase/
│   │       │       ├── FirestoreConfig.java     # Configuración Firebase
│   │       │       └── FirestoreTaskRepository.java # Repositorio Firebase
│   │       ├── service/
│   │       │   └── TaskService.java             # Servicio orquestador
│   │       └── controller/
│   │           └── TaskController.java          # Controlador REST
│   └── resources/
│       ├── application.properties               # Configuración principal
│       ├── firebase-adminsdk.json.example       # Ejemplo credenciales Firebase
│       └── firebase-adminsdk.json               # Credenciales reales (no incluir en git)
└── test/
    └── java/
        └── com/bbva/TpIntegradpr/
            └── TpIntegradprApplicationTests.java
```

## Arquitectura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │    │     Service     │    │   Repositories  │
│                 │    │                 │    │                 │
│  TaskController │───▶│   TaskService   │───▶│  MongoTaskRepo  │
│                 │    │                 │    │  FirestoreRepo  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │     Domain      │    │   NoSQL Stores  │
│                 │    │                 │    │                 │
│ TaskRequest     │    │      Task       │    │   MongoDB Atlas │
│ TaskResponse    │    │                 │    │ Firebase Firestore
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Pruebas Rápidas

### Crear tareas en MongoDB
```bash
curl -X POST http://localhost:8080/tasks/mongo \
  -H "Content-Type: application/json" \
  -d '{"title":"Demo MongoDB","completed":false}'
```

### Crear tareas en Firebase
```bash
curl -X POST http://localhost:8080/tasks/firebase \
  -H "Content-Type: application/json" \
  -d '{"title":"Demo Firestore","completed":true}'
```

### Obtener tareas por ID
```bash
# Reemplaza {id} con el ID retornado en la creación
curl http://localhost:8080/tasks/mongo/{id}
curl http://localhost:8080/tasks/firebase/{id}
```

## Diferencias Clave entre MongoDB y Firebase

### MongoDB Atlas (Spring Data MongoDB)
- **Configuración**: URI de conexión simple
- **Mapeo**: Automático con anotaciones `@Document`, `@Id`
- **Repositorio**: Interface que extiende `MongoRepository`
- **Operaciones**: Síncronas, métodos automáticos (save, findById, etc.)
- **IDs**: Generación automática si es null

### Firebase Firestore (Firebase Admin SDK)
- **Configuración**: Archivo de credenciales JSON
- **Mapeo**: Manual con Map<String, Object>
- **Repositorio**: Clase que usa Firestore client directamente
- **Operaciones**: Asíncronas con `ApiFuture`, manejo manual de futures
- **IDs**: Generación automática al agregar documento

## Comandos Útiles

### Maven
```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar aplicación
mvn spring-boot:run

# Crear JAR ejecutable
mvn clean package

# Ejecutar JAR
java -jar target/TpIntegradpr-0.0.1-SNAPSHOT.jar
```

### Verificación
```bash
# Verificar que la aplicación esté ejecutándose
curl http://localhost:8080/hello

# Verificar puerto
netstat -tlnp | grep 8080

# Verificar procesos Java
ps aux | grep java
```

## Notas Importantes
- La aplicación se ejecuta en el puerto 8080 por defecto
- El proyecto incluye DevTools para recarga automática durante el desarrollo
- **IMPORTANTE**: No incluir `firebase-adminsdk.json` en el control de versiones
- Configurar `.gitignore` para excluir archivos de credenciales
