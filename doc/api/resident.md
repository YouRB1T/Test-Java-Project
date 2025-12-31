### Жильцы
#### Создать жильца
*POST*  `api/residents`
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "resident": {
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "phone": "String",
        "apartmentId": "UUID",
        "email": "String"
    }
}
```
*Response*
```json
{
  "resident": {
    "residentId": "Int",
    "firstName": "String",
    "lastName": "String",
    "middleName": "String",
    "phone": "String",
    "apartmentId": "UUID",
    "email": "String"
  }
}
```