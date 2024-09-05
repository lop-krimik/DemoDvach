# Dvach project:
### Dvach project built on Java, Spring Boot (Web, Data JPA), Kafka, PostgreSQL, Liquibase, divided on several microservices and containerized with docker-compose

# User-service:
### This service is connected to database (table_name "users") and Kafka (topic: "user_topics"; user-service is producer). It starts on port 8082.

# Paste-service:
### This service is connected to database (table_name "paste") and Kafka (topic: "paste_topics"; paste-service is producer). It starts on port 8084.

# Notification-service:
### Listen both Kafka topics. Send email notifications when receiving a message about creation of user or paste.

# Database tables:

## Users:

| id (bigint) | username (varchar) | password (varchar) | email (varchar) |
|-------------|--------------------|--------------------|-----------------|

## Paste:

| id (bigint) | title (varchar) | text (varchar) | url (varchar) | email (varchar) | access (varchar) | lifecycle (varchar) | expired_time (timestamp) |
|-------------|-----------------|----------------|---------------|-----------------|------------------|---------------------|--------------------------|

#### Enums: Lifecycle (TEN_SEC, ONE_MIN, TEN_MIN, ONE_HOUR, THREE_HOUR, ONE_DAY, ONE_WEEK, ONE_MONTH, INFINITY), Access (PUBLIC, UNLISTED)


# Docker:
### To build project use `mvn clean install` base-domain first, than `mvn clean install` dvach project (you may skip tests, if they are not working).
### To launch application containers use `docker-compose up -d`.
### You can set "EMAIL_PASSWORD" and "EMAIL_USERNAME" environment variables (notification_service) for sending emails. 

# Liquibase:
### Liquibase create users and paste tables and add 1 user to table "users".

# HTTP-requests:
## User
### **1. Add user:**
#### Create user with parameters obtained from the request's body
#### http://localhost:8082/api/v1/user/addUser
#### PUT-method, JSON body example:
_{  
"username": "someUsername",  
"email": "someMail@gmail.com",  
"password": "somePassword"  
}_
### **2. Update username:**
#### Update user with id = user_id
####  http://localhost:8082/api/v1/user/updateUsername/id/{user_id}
#### PATCH-method, JSON body example:
_{  
"username": "anotherUsername",   
}_
### **3. Get all users:**
#### Returns all created users
#### http://localhost:8082/api/v1/user/getAllUsers
#### GET-method, body is not required
### **4. Get user by user_id:**
#### Returns user with certain user_id (id user exists)
#### http://localhost:8082/api/v1/user/getUserById/{user_id}
#### GET-method, body is not required
### **4. Delete user:**
#### Delete user with id = user_id
#### http://localhost:8082/api/v1/user/deleteUserById/{user_id}
#### DELETE-method, body is not required

## Paste
### **1. Add paste:**
#### Create paste with parameters obtained from the request's body
#### http://localhost:8084/api/v1/paste/addPaste
#### PUT-method, JSON body example:

_{  
"title": "Anecdote",  
"text": "What is the name of the dwarf walking at the end of the line?
Short circuit",  
"pasteDuration": "ONE_HOUR",  
"access": "PUBLIC",
"url": "SomeUrl",
"email": "someMail@gmail.com"   
}_
### **2. Get paste by paste_id:**
#### Returns paste with certain paste_id (id user exists)
#### http://localhost:8084/api/v1/paste/getPasteByPaste_id/{paste_id}
#### GET-method, body is not required
### **3. Get public pastes:**
#### Returns all public pastes ordered by lifecycle
#### http://localhost:8084/api/v1/paste/getPublicPastes
#### GET-method, body is not required
### **4. Update paste's text:**
#### Update paste with id = paste_id
#### http://localhost:8084/api/v1/paste/updatePasteText/paste_id/{paste_id}
#### PATCH-method, JSON body example:
_{  
"text": "anotherText"   
}_
### **5. Delete user:**
#### Delete paste with id = user_id
#### http://localhost:8084/api/v1/paste/{paste_id}
#### DELETE-method, body is not required


