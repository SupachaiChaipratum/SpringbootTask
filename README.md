[![Build Status](https://travis-ci.org/SupachaiChaipratum/SpringbootTask.svg?branch=master)](https://travis-ci.org/SupachaiChaipratum/SpringbootTask) [![Coverage Status](https://coveralls.io/repos/github/SupachaiChaipratum/SpringbootTask/badge.svg)](https://coveralls.io/github/SupachaiChaipratum/SpringbootTask)



# SpringBootTaskList
clone project and run this step
1. ./mvnw clean   or ( windrow : mvnw.cmd clean)
2. ./mvnw test   or ( windrow : mvnw.cmd test)
3. ./mvnw clean package  or ( windrow : mvnw.cmd clean install)
4. ./mvnw spring-boot:run or ( windrow : mvnw.cmd spring-boot:run)
    
        
        if you run some app on port 8080
        your can change port   ./mvnw spring-boot:run -Dserver.port=9999
    
    
5. go to url : localhost:8080

**API End point**



Get all Task List  : 
                   
                  GET Request → '/'  or '/tasks'

Get one by Id : 
    
                  GET Request → '/task/{id}'  ex. '/task/1'

Create new Task : 
                  
                  POST Request → '/task'
                  Header → Content-Type : application/json
                  Body → {“description” : “task test”, “pending” : “false” }
                  
Update Task : 
    
                  PATCH Request → '/task/'
                  Header → Content-Type : application/json
                  Body → { id : 1 , “description” : “update task test”, “pending” : “false” }
                  
                  
Update Pending : 
    
                  PUT Request → '/task/{id}'
                  

Delete Task : 
                  
                  DELETE Request → '/task/{id}'
              





