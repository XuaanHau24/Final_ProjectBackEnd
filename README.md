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


* Create a department
POST/ 
http://localhost:8070/api/v1/departments
content-type: JSON

{
    "name" : "Sport",
    "totalMember" : 10,
    "type" : "Dev",
    "accounts" : [
        {
            "username" : "usernamedep1",
            "firstName" : "firstNamedep1",
            "lastName" : "lastNamedep1",
            "email" : "usernamedep1@gmail.com",
            "role" : "EMPLOYEE",
            "password" : "12345678",
            "departmentId" : 2
        },
         {
            "username" : "usernamedep2",
            "firstName" : "firstNamedep2",
            "lastName" : "lastNamedep2",
            "email" : "usernamedep2@gmail.com",
            "role" : "EMPLOYEE",
            "password" : "12345678",
            "departmentId" : 2
        }

* Update a department
PUT/
http://localhost:8070/api/v1/departments/3
content-type: JSON
{
    "name" : "mana",
    "totalMember" : "5"
}

* DELETE a department
http://localhost:8070/api/v1/departments/9

* DELETE list
http://localhost:8070/api/v1/departments?ids=6,7,8
