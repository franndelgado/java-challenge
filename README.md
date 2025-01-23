# Java Challenge

## Herramientas utilizadas:
- **IDE**: IntelliJ IDEA Business utilizando Maven y Spring Boot
- **Base de datos**: MySQL
- **Java Development Kit (JDK)**: Version 17

## Pruebas de Postman:

### Parte 1: Caché en memoria Puntos de venta
- **Metodo**: GET
- **URL**: `http://localhost:8080/point-of-sale`
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
    "id": 22,
    "name": "Chaco"
}
```
- **URL**: `http://localhost:8080/point-of-sale`
- **Respuesta**: Successfully Created.
---
- **Metodo**: DELETE
- **URL**: `http://localhost:8080/point-of-sale/{id}`
- **Respuesta**: Successfully Deleted.
---
- **Metodo**: PUT
- **Request Body Example**:
```json
{
    "id": 3,
    "name": "Chaco"
}
```
- **URL**: `http://localhost:8080/point-of-sale`
- **Respuesta**: Successfully Updated.
---
### Parte 2: Caché en memoria Puntos de Costos

- **Método**: GET
- **URL**: `http://localhost:8080/costs`
- **Respuesta**:
```json
  [
    {
        "idA": 1,
        "idB": 2,
        "cost": 2
    },
    {
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
- **URL**: `http://localhost:8080/costs`
- **Respuesta**: New cost added.
---
- **Metodo**: DELETE
- **Parametros ejemplo:**
       *idA*: 1
       *idB*: 2
- **URL con parámetros incluidos**: `http://localhost:8080/costs?idA=1&idB=2`
- **Respuesta en caso de éxito**: Cost between id 1 and id 2 has been deleted.
- **Respuesta en caso de que no exista relación**: There is no direct path between id 1 and id 90.

- **Método**: GET
- **URL**: `http://localhost:8080/costs/search/{id}`
- **Respuesta**: Id {id} has direct path to Id X with cost: X
                 Id {id} has direct path to Id X with cost: X
                 ...
  
---
---

### Parte 3: Acreditaciones

- **Método**: POST
- **URL**: `http://localhost:8080/accreditations`
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
