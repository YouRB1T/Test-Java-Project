Использование данных из [api.md]

#### **User Management Service** 
Сервис управления пользователями - жителями домов
Через сервис я могу выполнять CRUD операции над информацией о жителях
**Ручки:**
* *POST* api/user/registration
* *POST* api/user/login

#### **Resident Service** +
Сервис управления жителями
Через сервис я могу выполнять CRUD операции над информацией о жителях
**Ручки:**
* GET /api/residents +
* GET /api/residents/{resident_id} +
* POST /api/residents +
* PUT /api/residents/{resident_id} +
* DELETE /api/residents/{resident_id} +
* GET /api/residents/apartments/{apartment_id} 
* GET /api/residents/buildings/{building_id} 

#### **Service Catalog Service** +
Сервис управления услугами и их исполнителями
Через данный сервис я могу выполнять CRUD операции над информацией о предоставляемых услугах
**Ручки**:
* *GET* api/services
* GET api/services/{id_employee}
* POST api/services/{id_employee}
* DELETE api/services/{id_employee}/{id_service}
* POST api/services
* PUT api/services/{service_id}
* GET /api/services/{service_id}
* DELETE /api/services/{service_id}

#### **Request Management Service**
Сервис управления заявками
Через данный сервис я могу выполнять CRUD операции над информацией о заявках
**Ручки**:
* POST api/request
* GET api/request/{uuid_user}
* PUT api/request/{id_request}
* GET /api/request
* GET /api/request/{request_id}
* DELETE /api/requests/{request_id}
* GET /api/requests/{request_id}/services
* POST /api/requests/{request_id}/services
* DELETE /api/requests/{request_id}/services/{service_id}
* GET /api/requests/{request_id}/changes
* POST /api/requests/{request_id}/changes

#### **Review Service**
Сервис управления отзывами
Через данный сервис я могу выполнять CRUD операции над информацией о отзывах над проделанной работой по заявкам
**Ручки**:
* *POST* api/review
* PUT api/review/{id_review}
* *GET* api/review/employee/{id_employee}
* GET /api/reviews
* GET /api/reviews/{review_id}
* DELETE /api/reviews/{review_id}

#### **Employee Service**
Сервис управления исполнителями услуг работников ТСЖ
Через данный сервис я могу выполнять CRUD операции над информацией о исполнителях услуг из сотрудников ТСЖ
**Ручки:**
* GET /api/employee/{employee_id}
* GET /api/employees
* POST /api/employees
* PUT /api/employees/{employee_id}
* DELETE /api/employees/{employee_id}
* GET /api/employees/{employee_id}/services
* POST /api/employees/{employee_id}/services
* DELETE /api/employees/{employee_id}/services/{service_id}
* GET /api/employees/offices/{office_id}
* POST /api/employees/offices/{office_id}

#### **Building Service** +
Сервис управления зданиями
Через данный сервис я могу выполнять CRUD операции над зданиями и квартирами
**Ручки:**
* GET /api/buildings +
* GET /api/buildings/{building_id} +
* POST /api/buildings +
* PUT /api/buildings/{building_id} +
* DELETE /api/buildings/{building_id} +
* GET /api/buildings/{building_id}/apartments +
* GET /api/buildings/{building_id}/residents

#### **Apartment Service** +
Сервис управления квартирами
Через данный сервис я могу выполнять CRUD операции над информацией о квартирах
**Ручки:**
* GET /api/apartments +
* GET /api/apartments/{apartment_id} + +
* POST /api/apartments + 
* PUT /api/apartments/{apartment_id} +
* DELETE /api/apartments/{apartment_id} +
* GET /api/apartments/{apartment_id}/residents

#### **Counterparty Service** +
Сервис управления контрагентами
Через данный сервис я могу выполнять CRUD операции над информацией о контрагентах
**Ручки:**
* DELETE /api/counterparties/{counterparty_id}/services/{service_id}
* POST /api/counterparties/{counterparty_id}/services
* GET /api/counterparties/{counterparty_id}/services
* DELETE /api/counterparties/{counterparty_id}+
* PUT /api/counterparties/{counterparty_id}+
* POST /api/counterparties+
* GET /api/counterparties/{counterparty_id}+
* GET /api/counterparties+

#### **Office Service** +
Сервис управления офисами
Через данный сервис я могу выполнять CRUD операции над информацией об офисах
**Ручки:**
* GET /api/offices + 
* GET /api/offices/{office_id} + +
* POST /api/offices +
* PUT /api/offices/{office_id} +
* DELETE /api/offices/{office_id} +
* GET /api/offices/buildings/{building_id} + +