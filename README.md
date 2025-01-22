# Java Challenge

## Herramientas utilizadas:
- **IDE**: IntelliJ IDEA Business utilizando Maven y Spring Boot
- **Base de datos**: MySQL
- **Java Development Kit (JDK)**: Version 17

## Pruebas de Postman:

### Parte 1: Caché en memoria Puntos de venta
-**Metodo:** GET
-**URL:** [localhost:8080/point-of-sale](http://localhost:8080/point-of-sale)
-**Respuesta:**
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

-**Metodo:** POST
-**Request Body Example**:
{
    "id": 22,
    "name": "Chaco"
}
-**URL:** [localhost:8080/point-of-sale](http://localhost:8080/point-of-sale)
-**Respuesta:** Successfully Created.

-**Metodo:** DELETE
-**URL:** [localhost:8080/point-of-sale](http://localhost:8080/point-of-sale/{id})
-**Respuesta:** Successfully Deleted.

-**Metodo:** PUT
-**Request Body Example**:
{
    "id": 3,
    "name": "Chaco"
}
-**URL:** [localhost:8080/point-of-sale](http://localhost:8080/point-of-sale)
-**Respuesta:** Successfully Updated.
