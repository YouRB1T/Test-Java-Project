## UUID ВО ВСЕХ ТАБЛИЦАХ

#### Таблица зданий
```sql
CREATE TABLE buildings ( 
building_id SERIAL PRIMARY KEY, 
address TEXT NOT NULL 
);
```
#### Таблица статусов запросов на услугу
```sql
CREATE TABLE request_statuses ( 
status_id SERIAL PRIMARY KEY, 
status_name VARCHAR(50) NOT NULL UNIQUE, 
description TEXT 
); 
```
#### Таблица контрагентов, исполняющих услуги
```sql
CREATE TABLE counterparties ( 
counterparty_id SERIAL PRIMARY KEY, 
organization_name VARCHAR(255) NOT NULL, 
tax_id VARCHAR(12) UNIQUE, -- ИНН 
registration_reason_code VARCHAR(9), -- КПП 
primary_state_registration_number VARCHAR(13), -- ОГРН 
legal_address TEXT, 
phone VARCHAR(20), 
email VARCHAR(100), 
contact_person VARCHAR(255), 
is_active BOOLEAN DEFAULT TRUE, 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 
```
#### Таблица услуг
```sql
CREATE TABLE services ( 
service_id SERIAL PRIMARY KEY, 
service_name VARCHAR(255) NOT NULL UNIQUE, 
description TEXT, 
is_active BOOLEAN DEFAULT TRUE 
); 
```
#### Таблица услуг контрагентов
```sql
CREATE TABLE counterparty_services ( 
service_id INTEGER NOT NULL, 
counterparty_id INTEGER NOT NULL, 
PRIMARY KEY (service_id, counterparty_id), 
FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE, 
FOREIGN KEY (counterparty_id) REFERENCES counterparties(counterparty_id) ON DELETE CASCADE
);
``` 

#### Таблица офисов ЖКХ
```sql
CREATE TABLE offices ( 
office_id SERIAL PRIMARY KEY, 
building_id INTEGER NOT NULL, 
phone VARCHAR(20), 
email VARCHAR(100), 
FOREIGN KEY (building_id) REFERENCES buildings(building_id) ON DELETE CASCADE 
);
``` 
#### Таблица квартир
```sql
CREATE TABLE apartments ( 
apartment_id SERIAL PRIMARY KEY, 
apartment_number INTEGER NOT NULL, 
building_id INTEGER NOT NULL, 
square_meters DECIMAL(8,2), 
number_of_rooms INTEGER, 
FOREIGN KEY (building_id) REFERENCES buildings(building_id) ON DELETE CASCADE 
); 
```
#### Таблица жителей
```sql
CREATE TABLE residents ( 
resident_id SERIAL PRIMARY KEY, 
first_name VARCHAR(100) NOT NULL, 
last_name VARCHAR(100) NOT NULL, 
middle_name VARCHAR(100), 
apartment_id INTEGER NOT NULL, 
phone VARCHAR(20),
email VARCHAR(100), 
FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 
```
#### Таблица работников ЖКХ
```sql
CREATE TABLE employes ( 
employee_id SERIAL PRIMARY KEY, 
first_name VARCHAR(100) NOT NULL, 
last_name VARCHAR(100) NOT NULL, 
middle_name VARCHAR(100), 
experience_years INTEGER, 
office_id INTEGER NOT NULL, 
phone VARCHAR(20),
email VARCHAR(100), 
hire_date DATE, 
is_active BOOLEAN DEFAULT TRUE, 
FOREIGN KEY (office_id) REFERENCES offices(office_id) ON DELETE SET NULL, 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
); 
```
#### Таблица услуг, оказываемых работниками
```sql
CREATE TABLE employee_services ( 
service_id INTEGER NOT NULL, 
employee_id INTEGER NOT NULL, 
PRIMARY KEY (service_id, employee_id), 
FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE 
); 
```
#### Таблица запросов на оказание услуг
```sql
CREATE TABLE requests ( 
request_id SERIAL PRIMARY KEY, 
content TEXT NOT NULL, -- Содержит название и описание
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
resident_id INTEGER NOT NULL,
status_id INTEGER NOT NULL DEFAULT 1, 
priority INTEGER CHECK (priority BETWEEN 1 AND 10), -- 1: Highest, 10: Lowest 
FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE, 
FOREIGN KEY (status_id) REFERENCES request_statuses(status_id)
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
); 
```
#### Таблица услуг и запросов
```sql
CREATE TABLE request_services ( 
service_id INTEGER NOT NULL, 
request_id INTEGER NOT NULL, 
PRIMARY KEY (service_id, request_id), 
FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE, 
FOREIGN KEY (request_id) REFERENCES requests(request_id) ON DELETE CASCADE
); 
```
#### Таблица изменения состояния запросов
```sql
CREATE TABLE request_changes ( 
change_id SERIAL PRIMARY KEY, 
change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
new_status_id INTEGER NOT NULL, 
old_status_id INTEGER NOT NULL, 
request_id INTEGER NOT NULL, 
change_notes TEXT, 
FOREIGN KEY (new_status_id) REFERENCES request_statuses(status_id), 
FOREIGN KEY (old_status_id) REFERENCES request_statuses(status_id), 
FOREIGN KEY (request_id) REFERENCES requests(request_id) ON DELETE CASCADE 
); 
```
#### Таблица отзывов
```sql
CREATE TABLE reviews ( 
review_id SERIAL PRIMARY KEY, 
rating INTEGER CHECK (rating BETWEEN 1 AND 10), 
content TEXT, 
written_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
request_id INTEGER NOT NULL UNIQUE, 
resident_id INTEGER NOT NULL, 
is_approved BOOLEAN, 
is_updated BOOLEAN,
last_date_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (request_id) REFERENCES requests(request_id) ON DELETE CASCADE, 
FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);
```

#### Таблица аккаунтов
```sql
CREATE TABLE accaunts (
    accaunt_id SERIAL PRIMARY KEY,
    login  VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    conterparty_id INTEGER,
    employee_id INTEGER,
    resident_id INTEGER,
    FOREIGN KEY (conterparty_id) REFERENCES counterparties(counterparty_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE SET NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE SET NULL,
);
```