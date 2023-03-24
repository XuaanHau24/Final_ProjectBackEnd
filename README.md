# Final_ProjectBackEnd
# Introduction
This is mock web project: manage account, department 

## Technologies
IDE: IntelliJ
Framework: Spring boot
JDK: corretto 11
Language level: default 11
Library: Maven

### Functions: CRUD, Login authen basic, Forgot password via Email

* Get information about account, department
http://localhost:8070/api/v1/accounts
http://localhost:8070/api/v1/departments

* Create an accounnt
POST/ 
http://localhost:8070/api/v1/accounts
content-type: JSON

{
    "username" : "newUser1",
    "firstName" : "first1",
    "lastName" : "last1",
    "email" : "hau.ntt2015@gmail.com",
    "password" : "123456789",
    "role" : "MANAGER",
    "departmentId" : 2
}

* Update an account
PUT/
http://localhost:8070/api/v1/accounts/3
content-type: JSON
{
    "username" : "updateUser3",
    "firstName" : "firstName3",
    "lastName" : "lastName3",
    "email" : "updateUser3@gmail.com",
    "departmentId" : "2",
    "password" : "1234",
    "role" : "MANAGER"
}

* DELETE an account
http://localhost:8070/api/v1/accounts/9

* DELETE list
http://localhost:8070/api/v1/accounts?ids=2,3,4

* Login
http://localhost:8070/api/v1/auth/login



