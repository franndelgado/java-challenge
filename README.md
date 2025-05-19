# Java Challenge

## Herramientas Utilizadas

- Java 17
- Visual Studio Code
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
- [Visual Studio Code] u otro IDE
- [Podman]
---

### 1. Clonar el repositorio

```bash
git clone https://github.com/franndelgado/java-challenge
cd java-challenge
```
### 2. Levantar contenedores:
```bash
podman-compose up --build
```
### 3. Probar los endpoints en Postman
    Importar la colección de Postman desde el archivo "Challenge Java.postman_collection.json".
---

## Pruebas de Postman:
### Registro y Login de usuario:
Primero se deberá crer un nuevo usuario:
- **Método**: POST
- **URL**: `http://localhost:8080/api/users/register`
- **Request Body Example**:
```json
{
   "username": "username",
   "password": "password123",
   "admin": true
}
```
Una vez creado el usuario, deberá loggearse:
- **Método**: POST
- **URL**: `http://localhost:8080/login`
- **Request Body Example**:
```json
{
   "username": "username",
   "password": "password123"
}
```
- **Respuesta**:
```json
{
    "message": "User username successfully logged in",
    "token": "token",
    "username": "username"
}
```
También se pueden obtener todos los usuarios registrados:
- **Método**: GET
- **URL**: `http://localhost:8080/api/users`

Una vez que se obtiene el token, ya pueden realizarse las distintas llamadas a la API.

### Parte 1: Caché en memoria Puntos de venta
- **Método**: GET
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
