# Java Challenge

Esta es una API REST para la gestión de puntos de venta, desarrollada con Spring Boot. Permite administrar la información de los puntos de venta como calcular rutas con costos mínimos entre ellos. Todo el sistema está contenerizado usando Podman y utiliza MySQL como base de datos principal.

## Herramientas Utilizadas

- Java 17  
- Spring Boot 3.4.2 
- MySQL 8  
- Redis (Para caching en memoria)  
- Podman y podman-compose  
- Maven  
- Swagger UI (Para documentación)  
- JUnit + Mockito (testing)

---

## Cómo levantar el proyecto:

Asegurarse de tener instaladas las siguientes herramientas en tu sistema:

- [Java 17]
- [Maven]
- [IntelliJ IDEA] u otro IDE
- [Podman]
---

### 1. Clonar el repositorio

```bash
git clone https://github.com/franndelgado/java-challenge
```
1. **Construir el proyecto**:

   `./mvnw clean package -DskipTests`

2. **Levantar los contenedores** con Podman:

   `podman-compose up --detach`

3. **Puerto de la aplicación** en `http://localhost:8080`.

### 4. Detener los contenedores

Para detener el proyecto, ejecuta:

`podman-compose down`

---

## Pruebas de Postman:

### Parte 1: Caché en memoria Puntos de venta
- **Metodo**: GET
- **URL**: `http://localhost:8080/api/point-of-sale`
- **Respuesta**:
```json
[
    {
        "id": 1,
        "name": "CABA"
    },
    {
        "id": 2,
        "name": "GBA_1"
    },
    {
        "id": 3,
        "name": "GBA_2"
    },
    ...
]
```
---

- **Metodo**: POST
- **Request Body Example**:
```json
{
    "name": "Formosa"
}
```
- **URL**: `http://localhost:8080/api/point-of-sale`
- **Respuesta**:
```json
{
    "id": 11,
    "name": "Formosa"
}
```
---
- **Metodo**: DELETE
- **URL**: `http://localhost:8080/api/point-of-sale/{id}`
- **Respuesta**: 200 OK
---
- **Metodo**: PUT
- **Request Body Example**: El nombre como se lo quiera actualizar, Ejemplo: "Chaco" para el id: 11
- **URL**: `http://localhost:8080/api/point-of-sale/{id}`
- **Respuesta**:
```json
{
    "id": 11,
    "name": "Chaco"
}
```
---
### Parte 2: Caché en memoria Puntos de Costos

- **Método**: GET
- **URL**: `http://localhost:8080/api/costs`
- **Respuesta**:
```json
  [
    {
        "id": 1,
        "idA": 1,
        "idB": 2,
        "cost": 2
    },
    {
        "id": 2,
        "idA": 1,
        "idB": 3,
        "cost": 3
    },...
  ]
```
---
- **Metodo**: POST
- **Request Body Example**:
```json
{
    "idA": 10,
    "idB": 9,
    "cost": 100
}
```
- **URL**: `http://localhost:8080/api/costs`
- **Respuesta**:
```json
{
    "id": 15,
    "idA": 10,
    "idB": 9,
    "cost": 100
}
```
---
- **Metodo**: DELETE
- **Parametros ejemplo:**
       *idA*: 1
       *idB*: 2
- **URL con parámetros incluidos**: `http://localhost:8080/api/costs?idA=1&idB=2`
- **Respuesta en caso de éxito**: 200 OK
- **Respuesta en caso de que no exista relación**: There is no direct path between id 1 and id 90.
---
- **Método**: GET
- **URL**: `http://localhost:8080/api/costs/search/{id}`
- **Respuesta**: Id {id} has direct path to Id X with cost: X
                 Id {id} has direct path to Id X with cost: X
                 ...
---
### Parte 3: Acreditaciones

- **Método**: POST
- **URL**: `http://localhost:8080/api/accreditations`
- **Request Body Example**:
```json
{
    "pointOfSaleId": 2,
    "amount": 1000
}
```
- **Respuesta**:
```json
{
    "pointOfSaleId": 2,
    "amount": 1000,
    "date": "2025-01-22",
    "pointOfSaleName": "GBA_1"
}
```
- **Método**: GET
- **URL**: `http://localhost:8080/api/accreditations`
- **Respuesta**:
```json
[
    {
        "pointOfSaleId": 2,
        "amount": 1000,
        "date": "2025-01-22",
        "pointOfSaleName": "GBA_1"
    },
    {
        "pointOfSaleId": 4,
        "amount": 350,
        "date": "2025-01-22",
        "pointOfSaleName": "Santa Fe"
    },
    ...
]
```
Para más facilidad el archivo "Challenge Java.postman collection.json" contienede la colección creada de Postman para probar cada endpoint.

---
## Documentación de la API

Una vez corriendo la aplicación podes mirar la documentación de Swagger en: (http://localhost:8080/swagger-ui/index.html)
