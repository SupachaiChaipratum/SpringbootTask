[![Build Status](https://travis-ci.org/SupachaiChaipratum/SpringbootTask.svg?branch=master)](https://travis-ci.org/SupachaiChaipratum/SpringbootTask) [![Coverage Status](https://coveralls.io/repos/github/SupachaiChaipratum/SpringbootTask/badge.svg)](https://coveralls.io/github/SupachaiChaipratum/SpringbootTask)



# SpringBootTaskList
clone project and run this step
1. mvn clean
2. mvn test
3. mvn clean install 
4. mvn spring-boot:run 
5. go to url : localhost:8080

**API End point**



Get all Task List  : 
                   
                  GET Request → '_/'  or '/tasks'_

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
                  

Delete Task : 
                  
                  DELETE Request → '/task/id'
              





