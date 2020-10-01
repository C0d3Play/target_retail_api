# target_retail_api
This project containerizes two microservices along with its datastore.
Contents
1. myRetail RESTful service 
2. ProductNameService RESTful service

#myRetail
Provides RESTful methods
I HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
  -> Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
  -> Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) 
(Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}} 

II HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

#ProductNameService
A spring boot application.
I HTTP GET request at /products/{id}/name and delivers the product name.

Database used for datastore is mongodb.

#How to run
/myRetail/scripts/run.sh (or)  /myRtail/scripts/run.sh
Running this file should start 3 different docker containers and make rest services up.

#How to test myRetail API
http://localhost:8080/api/products/123
Auth: user:password  (works for GET)
Auth: admin:admin	(works for GET & PUT)
