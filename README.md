# Microservicio de Banco

## Descripción
Este proyecto es un microservicio desarrollado en Spring Boot que simula un sistema bancario centrado en tarjetas de crédito. La aplicación permite a los usuarios:
- Crear tarjetas de crédito personalizadas
- Visualizar las tarjetas creadas
- Realizar operaciones de pago con las tarjetas

## Tecnologías
- Java 11+
- Spring Boot
- Thymeleaf (para vistas)
- Spring Data JPA
- Maven

## Requisitos previos
- JDK 11 o superior
- Maven
- Base de datos (PostgreSQL/MySQL/H2)

## Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com /tu-usuario/banco-microservicio.git
   cd banco-microservicio
   ```

## Funcionalidades

### Flujo de usuario

1. El usuario accede a `/card` para ver el formulario de creación de tarjetas
2. Completa y envía el formulario con los datos de la tarjeta
3. El sistema valida y guarda la información de la tarjeta
4. Se redirige a `/card/list` donde el usuario puede visualizar todas sus tarjetas creadas
5. El usuario puede seleccionar una tarjeta para realizar pagos a través del endpoint `/api/v1/payment`

## Endpoints Web

### Gestión de tarjetas

### `GET /card`

- **Descripción:** Muestra el formulario para crear una nueva tarjeta de crédito
- **Vista renderizada:** `card/card.html`
- **Acceso:** Navegador web

### `GET /card/list`

- **Descripción:** Muestra la lista de tarjetas de crédito creadas por el usuario
- **Vista renderizada:** `card/list-card.html`

### Visuzalizar transacciones

### `GET /transaction`

- **Descripción:** Muestra el listado de transacciones que se han realizado
- **Vista renderizada:** `card/view-transaction.html`
- 
### `GET /transaction/{id}`

- **Descripción:** Muestra el detalle de una trasansacción realiada
- **Vista renderizada:** `card/only-trasanction.html`

## API REST

### Realizar un pago

```http
POST /api/v1/payment
```

| Parámetro          | Tipo   | Descripción                                                 |
|--------------------|--------|-------------------------------------------------------------|
| `value`            | string | **Requerido**. Monto del pago en moneda local               |
| `card`             | object | **Requerido**. Datos de la tarjeta de crédito               |
| `card.cardNumber`  | string | **Requerido**. Número de la tarjeta de crédito              |
| `card.expiryMonth` | string | **Requerido**. Mes de vencimiento de la tarjeta (MM)        |
| `card.expiryYear`  | string | **Requerido**. Año de vencimiento de la tarjeta (YY)        |
| `card.cvv`         | string | **Requerido**. Código de seguridad (CVV) de la tarjeta      |
| `user`             | object | **Requerido**. Información del usuario que realiza el pago  |
| `user.name`        | string | **Requerido**. Nombre completo del titular de la tarjeta    |
| `user.email`       | string | **Requerido**. Correo electrónico para notificaciones       |
| `user.document`    | string | **Requerido**. Número de documento de identidad del usuario |


### Realizar un pago

```http
GET /api/v1/payment
```

#### Ejemplo de solicitud

```json
{
   "value": 50000,
   "card": {
      "cardNumber": "4844 7442 6365 7972",
      "expiryMonth": "06",
      "expiryYear": "27",
      "cvv": "079"
   },
   "user": {
      "name": "casandra",
      "email": "casa2@gamil.com",
      "document": "1324334"
   }
}
```

#### Posibles respuestas

| Código | Descripción                                              |
|--------|----------------------------------------------------------|
| 200    | Pago procesado correctamente                             |
| 400    | Error en los datos de la solicitud                       |
| 404    | Tarjeta no encontrada                                    |
| 402    | Tarjeta inválida o sin fondos suficientes                |
| 500    | Error interno del servidor                               |


### Respuesta 

posibles repuestas de pago:

- SUCCESS
- FAILED
- WAITING

```
{
    "id": 9,
    "dateTime": "2025-05-20T20:48:31.6441402",
    "description": "Pago realizado con exito",
    "statusPayment": "SUCCESS",
    "card": {
        "id": 2,
        "cardType": "DISCOVER",
        "cardNumber": "4844 7442 6365 7972",
        "cardName": "Juan Pereira",
        "valueCard": 9500.00,
        "expiryMonth": "06",
        "expiryYear": "27",
        "cvv": "079"
    },
    "user": {
        "id": 2,
        "name": "casandra",
        "email": "casa2@gamil.com",
        "document": "1324334"
    },
    "amount": 500
}
```