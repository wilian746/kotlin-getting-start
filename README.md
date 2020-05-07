# Smart-Point Kotlin getting-start

An easy template to access and get your data on MongoDB using Kotlin Language.


## Getting Started
### Pre-requirements
In your machine up an image docker of **MongoDB** or install locally. By default this connection not appear modification, when start application connect on database `localhost:27017` without security connection, to add security and modify host change file `application.properties` on directory `src/main/resources/application.properties` 

Before up database we recommended open this project on the [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) to install dependeces and run project

### Init server
Start main of project on directory :
```sh
src/main/kotlin/com/kottlingettingstart/main/MainApplication.kt
```

Before this services will show this log
`[...] Tomcat initialized with port(s): 8080 (http)`

## Usage
When server start autommatic he delete all content on the collections: `company`, `release`, `employee`. After generate new data and print on console yours Ids see example:
```text
    Company ID: 5eb17b420751352ef8fe70ff
    Admin ID: 5eb17b420751352ef8fe7100
    Employee ID: 5eb17b420751352ef8fe7101
```

### Health
### Get_Release_By_id
This route return specific release in your database
```text
    GET - http://localhost:8080/api/release/5eb1722e9c727243a06a1c9c
```

### Get_Release_By_employeeId
This route return all releases by employee specific on route
```text
    GET - http://localhost:8080/api/release/employee/5eb172249c727243a06a1c9b?page=0&size=3&sort_column=date&sort_type=ASC
```


### Post_Release
This route create on release in your database
```text
    POST - http://localhost:8080/api/release
    {
    	"date": "2020-04-30 08:30:58",
    	"type": "WORK_START",
    	"employeeId": "5eb172249c727243a06a1c9b"
    }
```


### Put_Release
This route UPDATE on release in your database
```text
    PUT - http://localhost:8080/api/release/5eb1722e9c727243a06a1c9c
    {
    	"date": "2020-04-30 19:30:58",
    	"type": "WORK_END",
    	"employeeId": "5eb172249c727243a06a1c9b"
    }
```

#### Delete
This route remove on release in your database
```text
    DELETE - http://localhost:8080/api/release/5eb1722e9c727243a06a1c9c
```
