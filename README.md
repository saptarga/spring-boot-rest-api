# PRIVYID - PRETEST - BACKEND ENGINEER

## Description
Simple project pretest backend engineer using Java Spring Boot and JWT Authentication

## Prerequisites
- Java JDK 8
- Database PostgreSql
- Maven

## Instalation
Step for installation:
```sh
# Clone this project from gitlab
git clone git@gitlab.com:saptarga/privyid-pretest.git

# Clears the target directory and builds the project
mvn clean install
```

## Project Structure
```
.                                        # main directory project 
+-- java
|   +-- dto                             # Define A Data Transfer Object
|   +-- endpoint                        # Rest controllers that handle request/responses
|   +-- entity                          # Define domain models or entities
|   +-- exception                       # Define exception handle
|   +-- repository                      # Talks to data source directly, has operations commonly known as CRUD. It could be simple jdbc, JPA, or even file access
|   +-- security                        # Configuration for security
|   +-- service                         # Business logic abstractions, this layer has no idea how to communicate with datasource.
|   +-- statval                         # Define static value, such as enum variable or constanta variable
|   +-- MainClass.java                  # App starting point
+-- resources 
|   +-- db.migration                    # Db migration script
|   +-- application.properties          # Configurations files               
```

## Configuration
Step for configuration:

- Create new database in postgresql with database name `privyid_pretest`.
- Set database name, user, and password in `application.properties`.
- For db migration and seeding data will be created automatically when application running for first time.

## Run Project 
You can start this project using
```sh
mvn clean spring-boot:run
```

## Example Request Rest API

### Login User
Request
```sh

POST /v1/auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 62

{
    "username": "sapta_arga",
    "password": "123456789"
}
```
Response
```
{
  "token": {{authToken}},
  "type": null,
  "id": 2,
  "username": "sapta_arga",
  "email": "sapta_arga@gmail.com",
  "roles": [
    "ROLE_USER"
  ]
}
```

## Deposit Money
Request
```sh
POST /v1/user-balance/deposit-money HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 93
Authorization: {{authToken}}

{
    "amount": 1000000,
    "bankCode": "3322030300020244"
}
```
Response 
```sh
{
  "username": "sapta_arga",
  "activity": "DEPOSIT_MONEY",
  "balance": 2050000,
  "depositMoney": 1000000
}
```

## Transfer Money
Request
```sh
POST /v1/user-balance/transfer-money HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 93
Authorization: {{authToken}}

{
    "transferTo" : "dwi_asih",
    "amount": 1000000,
    "bankCodeTo": "3322030300020223",
    "bankCodeFrom" : "3322030300020244"
}
```
Response
```sh
{
  "transferFrom": "sapta_arga",
  "transferTo": "dwi_asih",
  "amount": 1000000
}
```

### Logout User
Request
```sh
POST /v1/auth/logout HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: {{authToken}}
```
Response
```sh
Logout Success
```

### Example Error Response API 
Error message if you don't login for access API
```sh 
{
  "timestamp": "2021-08-04T08:03:06.024+00:00",
  "status": 401,
  "error": "Unauthorized",
  "path": "/v1/user-balance/deposit-money"
}
```
Error message if you access API with inappropriate roles
```sh 
{
  "timestamp": "2021-08-04T08:08:57.309+00:00",
  "status": 403,
  "error": "Forbidden",
  "path": "/v1/user-balance/deposit-money"
}
```

## Client Http

The example above can you try in `privy_pretest_client.http`

## Author 
Created and maintained by saptarga ([@saptarga](https://www.linkedin.com/in/saptarga)).