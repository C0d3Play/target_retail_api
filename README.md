# target_retail_api
This project containerizes two microservices along with its datastore.
Contents:
1. myRetail RESTful service 
2. ProductNameService RESTful service

# myRetail
Provides RESTful methods
1. HTTP GET request at /products/{id} and delivers product data as JSON 
  Example Response: 
      {"id":123,"name":"Friends Poster","current_price":{"value": 13.49,"currency_code":"USD"}} 

2. HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

# ProductNameService
A spring boot application.
1. HTTP GET request at /products/{id}/name and delivers the product name.

# Database used for datastore is mongodb.

# How to run
/myRetail/scripts/run.sh (or)  /myRtail/scripts/run.sh
Running this file should start 3 different docker containers and make rest services up.

# How to test myRetail API
http://localhost:8080/api/products/123
Basic Authenthication: 
1. user:password  (works for GET)
2. admin:admin	(works for GET & PUT)
