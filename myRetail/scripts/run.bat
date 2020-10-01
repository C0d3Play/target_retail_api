echo on
docker build -f docker\myretail-dockerfile -t myretail:0.0.1 docker\
docker build -f docker\targetapi-dockerfile -t targetapi:0.0.1 docker\
docker-compose up
pause 
