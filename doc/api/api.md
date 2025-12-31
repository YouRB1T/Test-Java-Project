Соответствуют сущностям [[db.md]]

### Выполнение функционала контрактами

#### Отправление заявки
*POST* api/request
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Requset*
```json
{
"request": {
		"name": "String",
		"description": "String",
		"services": [
		"service_id_1",
		"service_id_2"
		],
		"user": "uuid_user",
		"priority": "Int"
	}
}
```
*Response*
```json
{
	"request": {
		"name": "String",
		"description": "String",
		"employeesOrConterparties": {
			"employeeOrConterpartie1": {
				"name": "String",
				"serviceName": "String",
			"officeAdres": "String",
				"serviceName": "String"
			},
			"employeeOrConterpartie2": {
				"name": "String",
				"serviceName": "String",
				"officeAdres": "String",
				"serviceName": "String"
}
},
		"priority": "Int",
		"status":"String",
		"createdAt": "DateTime"
	}
}
```
#### Получение услуг для выбора
*GET* api/service
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json{
	"sevices": {
		service1: {
			"id_service": "Int",
			"name": "String",
			"description": "String"
		},
		service2: {
			"id_service": "Int",
			"name": "String",
			"description": "String"
		}
...
	}
}
```
#### Показывать все заявки жильца
*GET* api/request/{uuid_user}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
	"requests": {
		"request_1": {
			"requestId": "Integer",
			"name": "String",
			"description": "String",
			"employeesOrConterparties": {
				"employeeOrConterpartie1": {
					"name": "String",
					"serviceName": "String",
					"officeAdres": "String",
					"serviceName": "String"
				},
				"employeeOrConterpartie2": {
					"name": "String",
					"serviceName": "String",
					"officeAdres": "String",
					"serviceName": "String"
				}
			},
	"priority": "Int",
			"status":"String",
			"createdAt": "DateTime"
		}
	}	
}
```
#### Оставить отзыв о выполнении заявки
*POST* api/review
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json```
*Requset*
```json
{
	"review": {
		"rating": "Int",
		"content": "String",
		"requestId": "Int",
		"userId": "Int",
		"isAproced": "Boolean"
	}
}
```
*Response*
```json
{
	"review": {
	"reviewId": "Int",
		"rating": "Int",
		"content": "String",
		"requestId": "Int",
		"userId": "Int",
		"isAproced": "Boolean",
"isUpdated": "Boolean"
	}
}
```
#### Возможностьизменить отзыв
PUT api/review/{id_review}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Requset*
```json
{
	"review": {
		"rating": "Int",
		"content": "String",
		"requestId": "Int",
		"userId": "Int",
		"isAproced": "Boolean",
"isUpdated": "Boolean"
	}
}
```
*Response*
```json
{
	"review": {
		"reviewId": "Int",
		"rating": "Int",
		"content": "String",
		"requestId": "Int",
		"userId": "Int",
		"isAproced": "Boolean",
		"isUpdated": "Boolean"
	}
}
```

#### Показать все отзывы об исполнителе
*GET* api/review/employee/{id_employee}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "reviews": [
        {
         "reviewId": "Int",
            "rating":"Int",
            "content": "String",
            "requestId": "Int",
            "residentName": "String",
            "apartmentNumber": "Int",
            "writtenDate": "DateTime",
            "isApproved": "Boolean"
        },
        ...
]
}
```
#### Показывать все заявки исполнителя
*GET* api/employee/{id_employee}/request
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "requests": [
        {
            "requestId": "Int",
            "name": "String",
            "description": "String",
            "resident": {
                "name": "String",
                "apartmentNumber": "Int",
                "phone": "String"
            },
            "services": [
                {
                    "serviceId": "Int",
                    "name": "String"
               }
            ],
            "priority": "Int",
            "status": "String",
            "createdAt": "DateTime",
            "updatedAt": "DateTime"
        }
    ]
}
```

#### Обновлять статус заявки и составлять комментарий после изменения статуса
*PUT* api/request/{id_request}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "request": {
        "statusId": "Int",
        "changeNotes": "String"
    }
}
```
*Response*
```json
{
    "request":{
        "requestId": "Int",
        "status": "String",
        "changeNotes": "String",
        "updatedAt": "DateTime"
    }
}
```

#### Показывать все услуги исполнителя
GET api/service/{id_employee}
```txtHeaders
Authorization:Bearer <JWT-Token>
```
*Response*
```json
{
    "services": [
        {
            "serviceId": "Int",
            "name": "String",
            "description": "String",
            "isActive": "Boolean"
        }
    ]
}
```
#### Возможность добавлять услуги исполнителя
POST api/service/{id_employee}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "service": {
        "serviceId": "Int"
    }
}
```
*Response*
```json
{
    "employeeService": {
        "employeeId": "Int",
        "serviceId": "Int",
        "addedAt": "DateTime"
    }
}
```

#### Возможность удалять услуги исполнителя
DELETE api/service/{id_employee}/{id_service}
```txt
HeadersAuthorization:Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Service removed from employee successfully",
    "removedServiceId": "Int",
    "removedServiceName": "String"
}
```

#### Возможность добавлять услуги
POST api/service
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
   "service": {
        "name": "String",
        "description": "String"
    }
}
```
*Response*
```json
{
    "service": {
"serviceId":"Int",
"name": "String",
        "description": "String",
        "isActive": "Boolean",
        "createdAt": "DateTime"
   }
}
```

#### Возможность обновить услугу
PUT api/service
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
  "service": {
	   "serviceId": "Int",
        "name": "String",
        "description": "String",
        "isActive": "Boolean"
    }
}
```
*Response*
```json
{
"service": {
       "serviceId": "Int",
        "name": "String",
       "description": "String",
        "isActive": "Boolean",
"updatedAt": "DateTime"
    }
}
```
#### Регистрация пользователя
*POST* api/user/registration
```txt
HeadersAuthorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
   "user": {
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "apartmentNum": "Int",
        "address": "String",
        "phone": "String",
        "email": "String",
        "password": "String"
    }
}
```
*Response*
```json
{
    "user": {
        "residentId": "Int",
        "firstName": "String",
        "lastName": "String",
        "email": "String",
        "phone": "String",
        "apartmentNumber": "Int",
        "address": "String",
        "createdAt": "DateTime"
    },
    "token": "JWT-Token"
}
```
#### Логин пользователя
*POST* api/user/login
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "credentials": {
        "email": "String",
        "password": "String"
    }
}
```
*Response*
```json
{
    "user": {
        "residentId": "Int",
        "firstName": "String",
        "lastName": "String",
        "email": "String",
        "apartmentNumber": "Int"
    },
    "token":"JWT-Token"
}
```
#### Получить все здания
GET /api/buildings
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "buildings": [
        {
            "buildingId": "Int",
            "address": "String"
        }
    ]
}
```

#### Получить здание по ID
GET /api/buildings/{building_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "building": {
        "buildingId": "Int",
        "address": "String"
    }
}
```

#### Создать новое здание
POST /api/buildings
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "building": {
        "address": "String"
    }
}
```
*Response*
```json
{
    "building": {
        "buildingId": "Int",
        "address": "String"
    }
}
```

#### Обновить здание
PUT /api/buildings/{building_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "building": {
        "address": "String"
    }
}
```
*Response*
```json{
    "building": {
        "buildingId": "Int",
        "address": "String"
    }
}
```

#### Удалить здание
DELETE /api/buildings/{building_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Building deleted successfully"
}
```

#### Получить все офисы
GET /api/offices
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "offices": [
        {
            "officeId": "Int",
            "buildingId": "Int",
            "phone": "String",
            "email": "String"
        }
    ]
}
```

#### Получить офисы по зданию
GET /api/buildings/{building_id}/offices
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "offices": [
        {
            "officeId": "Int",
            "buildingId": "Int",
            "phone": "String",
            "email": "String"
        }
    ]
}
```

#### Получить офис по ID
GET /api/offices/{office_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "office": {
        "officeId": "Int",
        "buildingId": "Int",
        "phone": "String",
        "email":"String"
    }
}
```

#### Создать новый офис
POST /api/offices
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "office": {
        "buildingId": "Int",
        "phone":"String",
        "email": "String"
    }
}
```
*Response*
```json
{
    "office": {
        "officeId": "Int",
        "buildingId": "Int",
        "phone": "String",
        "email": "String"
    }
}
```

#### Обновить офис
PUT /api/offices/{office_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "office": {
        "buildingId": "Int",
        "phone": "String",
        "email": "String"
}
}
```
*Response*
```json
{
    "office": {
        "officeId": "Int",
        "buildingId": "Int",
        "phone": "String",
        "email": "String"
    }
}
```

#### Удалить офис
DELETE /api/offices/{office_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Office deleted successfully"
}
```

### Получить всех контрагентов
GET /api/counterparties
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "counterparties": [
        {
            "counterpartyId": "Int",
            "organizationName": "String",
           "taxId":"String",
            "registrationReasonCode": "String",
            "primaryStateRegistrationNumber": "String",
            "legalAddress": "String",
            "phone": "String",
            "email": "String",
            "contactPerson": "String",
            "isActive": "Boolean",
            "createdAt": "DateTime"
        }
    ]
}
```

#### Получить контрагента по ID
GET /api/counterparties/{counterparty_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "counterparty": {
        "counterpartyId": "Int",
        "organizationName": "String",
       "taxId": "String",
        "registrationReasonCode": "String",
        "primaryStateRegistrationNumber": "String",
        "legalAddress": "String",
        "phone": "String",
        "email": "String",
        "contactPerson": "String",
        "isActive": "Boolean",
        "createdAt": "DateTime"
    }
}
```

#### Создать нового контрагента
POST /api/counterparties
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json{
"counterparty": {
        "organizationName": "String",
        "taxId": "String",
        "registrationReasonCode": "String",
        "primaryStateRegistrationNumber": "String",
        "legalAddress": "String",
        "phone": "String",
        "email": "String",
        "contactPerson":"String",
        "isActive": "Boolean"
}
```
*Response*
```json
{
   "counterparty": {
        "counterpartyId": "Int",
        "organizationName": "String",
        "taxId": "String",
        "registrationReasonCode": "String",
        "primaryStateRegistrationNumber": "String",
        "legalAddress": "String",
        "phone": "String",
        "email": "String",
        "contactPerson": "String",
        "isActive": "Boolean",
        "createdAt": "DateTime"
    }
}
```

#### Обновить контрагента
PUT /api/counterparties/{counterparty_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "counterparty": {
        "organizationName": "String",
        "taxId":"String",
        "registrationReasonCode": "String",
        "primaryStateRegistrationNumber": "String",
        "legalAddress": "String",
        "phone": "String",
        "email": "String",
        "contactPerson": "String",
        "isActive": "Boolean"
    }
}
```
*Response*
```json
{
  "counterparty": {
        "counterpartyId": "Int",
        "organizationName": "String",
        "taxId": "String",
        "registrationReasonCode": "String",
        "primaryStateRegistrationNumber": "String",
        "legalAddress": "String",
        "phone": "String",
        "email": "String",
        "contactPerson": "String",
        "isActive": "Boolean",
        "createdAt": "DateTime"
    }
}
```

#### Удалить контрагента
DELETE /api/counterparties/{counterparty_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Counterparty deleted successfully"
}
```

#### Получить все услуги контрагента
GET /api/counterparties/{counterparty_id}/services
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "services": [
        {
            "serviceId": "Int",
            "serviceName": "String",
            "description": "String",
            "isActive": "Boolean"
        }
    ]
}
```

#### Назначить услугу контрагенту
POST /api/counterparties/{counterparty_id}/services
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
"service": {
        "serviceId": "Int"
    }
}
```
*Response*
```json
{
    "counterpartyService": {
        "serviceId": "Int",
        "counterpartyId": "Int",
        "assignedAt": "DateTime"
    }
}
```

#### Удалить услугу контрагента
DELETE /api/counterparties/{counterparty_id}/services/{service_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Serviceremoved from counterparty successfully",
    "removedServiceId": "Int"
}
```

### Получить всех работников
GET /api/employees
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "employees": [
        {
            "employeeId": "Int",
            "firstName": "String",
            "lastName": "String",
            "middleName": "String",
            "experienceYears": "Int",
            "officeId": "Int",
            "phone": "String",
            "email": "String",
            "hireDate": "DateTime",
            "isActive": "Boolean",
            "createdAt": "DateTime"
        }
    ]
}
```

#### Получить работников по офису
GET /api/offices/{office_id}/employees
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "employees": [
        {
            "employeeId": "Int",
            "firstName": "String",
            "lastName": "String",
            "middleName": "String",
            "experienceYears": "Int",
            "officeId": "Int",
            "phone": "String",
            "email": "String",
           "hireDate": "DateTime",
            "isActive": "Boolean",
            "createdAt": "DateTime"
        }
    ]
}
```

#### Получить офис работника
GET /api/employees/{employee_id}/office
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "office": {
        "officeId": "Int",
        "buildingId": "Int",
        "phone": "String",
        "email": "String"
    }
}
```

#### Создать работника
POST /api/employees
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "employee": {
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "experienceYears": "Int",
        "officeId": "Int",
        "phone": "String",
        "email": "String",
        "hireDate": "DateTime",
        "isActive": "Boolean"
    }
}
```
*Response*
```json
{
    "employee": {
        "employeeId": "Int",
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "experienceYears": "Int",
        "officeId": "Int",
        "phone": "String",
        "email": "String",
        "hireDate": "DateTime",
        "isActive": "Boolean",
        "createdAt": "DateTime"
    }
}
```

#### Обновить работника
PUT /api/employees/{employee_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "employee": {
        "firstName":"String",
        "lastName": "String",
        "middleName": "String",
        "experienceYears": "Int",
        "officeId": "Int",
        "phone": "String",
        "email": "String",
        "hireDate": "DateTime",
        "isActive": "Boolean"
    }
}
```
*Response*
```json
{
    "employee": {
        "employeeId": "Int",
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "experienceYears": "Int",
        "officeId": "Int",
        "phone":"String",
        "email": "String",
        "hireDate": "DateTime",
        "isActive": "Boolean",
        "createdAt": "DateTime"
    }
}
```

#### Удалить работника
DELETE /api/employees/{employee_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Employee deleted successfully"
}
```

#### Получить все квартиры в здании
GET /api/buildings/{building_id}/apartments
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json{
   "apartments": [
        {
            "apartmentId": "Int",
            "apartmentNumber": "Int",
            "buildingId": "Int",
            "squareMeters": "Decimal",
            "numberOfRooms": "Int"
        }
    ]
}
```

#### Получить квартиру по ID
GET /api/apartments/{apartment_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "apartment": {
        "apartmentId": "Int",
        "apartmentNumber": "Int",
        "buildingId": "Int",
       "squareMeters": "Decimal",
        "numberOfRooms": "Int"
    }
}
```

#### Создать квартиру
POST /api/apartments
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "apartment": {
        "apartmentNumber": "Int",
        "buildingId": "Int",
        "squareMeters": "Decimal",
        "numberOfRooms": "Int"
    }
}
```
*Response*
```json
{
    "apartment": {
        "apartmentId": "Int",
       "apartmentNumber": "Int",
        "buildingId": "Int",
        "squareMeters": "Decimal",
        "numberOfRooms": "Int"
    }
}
```

#### Обновить квартиру
PUT /api/apartments/{apartment_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
Content-Type: application/json
```
*Request*
```json
{
    "apartment": {
        "apartmentNumber": "Int",
        "buildingId": "Int",
        "squareMeters": "Decimal",
        "numberOfRooms": "Int"
    }
}
```
*Response*
```json
{
    "apartment": {
        "apartmentId": "Int",
        "apartmentNumber": "Int",
        "buildingId": "Int",
"squareMeters": "Decimal",
        "numberOfRooms": "Int"
    }
}
```

#### Удалить квартиру
DELETE /api/apartments/{apartment_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "message": "Apartment deleted successfully"
}
```

#### Получить всех жильцов квартиры
GET /api/residents/{apartment_id}
```txt
Headers
Authorization: Bearer <JWT-Token>
```
*Response*
```json
{
    "apartment": {
      "apartmentId": "UUID",
      "apartmentNumber": "Int",
      "building": {
        "buildingId": "Int",
        "address": "String"
      },
      "squareMeters": "Decimal"
    },
    "residents": [
      {
        "residentId": "Int",
        "firstName": "String",
        "lastName": "String",
        "middleName": "String",
        "phone": "String",
        "email": "String"
      },
      ...
    ]
}
```