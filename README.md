# material-servicio

Microservicio de gestión de materiales reciclables para la plataforma Karübag.

## Descripción
Gestiona el catálogo de materiales reciclables (cartón, plástico, vidrio, metal, etc.) con su precio por kilo actualizado.

## Tecnologías
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- PostgreSQL (Neon)

## Puerto
`8083`

## Base de datos
`karubag_material`

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/materiales | Listar todos los materiales |
| GET | /api/materiales/activos | Listar materiales activos |
| GET | /api/materiales/tipo/{tipo} | Listar por tipo |
| GET | /api/materiales/{id} | Obtener material por ID |
| POST | /api/materiales | Crear material |
| PUT | /api/materiales/{id} | Actualizar material |
| DELETE | /api/materiales/{id} | Eliminar material |

## Tipos de material
`CARTON`, `PLASTICO`, `VIDRIO`, `METAL`, `PAPEL`, `ORGANICO`, `OTRO`

## Cómo ejecutar
```bash
./mvnw spring-boot:run
```

## Variables de entorno
```
spring.datasource.url=jdbc:postgresql://<host>/karubag_material
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
```