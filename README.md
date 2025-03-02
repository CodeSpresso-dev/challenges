# Challenge

## Microservices Architecture with Customer-Service & Order-Service

### This project implements a microservices architecture consisting of two services:
1. Customer-Service: Handles customer data, developed by using TDD approach, good handing exceptions and OpenAPI recommended documentation.
2. Order-Service: Implements CQRS using Axon Framework, allowing order creation and validating customers via inter-service communication using FeignClient.

#  🚀Project Features
## Customer-Service
### ✅ Developed with TDD (Test-Driven Development).
### ✅ Comprehensive exception handling.
### ✅ OpenAPI-based API documentation.

## Order-Service
### ✅ Implements CQRS with Axon Framework.
### ✅ Creates orders and validates customers via Customer-Service using FeignClient.
### ✅ Uses Event Sourcing for better scalability.

## Dockerization
### Both microservices use <u>Google Jib</u> to create Docker images. Before running the services, ensure Docker Engine is running on your local system.

#### Build Docker Images
~~~
mvn compile jib:dockerBuild
~~~

## API Documentation
### Once the services are up and running, access Swagger UI for API testing:
 [customer-service](http://localhost:8081/swagger-ui/index.html)
 
### Once the services are up and running, access axon server dashboard:
[axon-server](http://localhost:8024/)

### Here I provide some test data as a template and make you easy-to-test :)
### First import this data from collection section then you are ready to test
[postman-data](https://github.com/CodeSpresso-dev/challenges/tree/main/Container/postman-data)