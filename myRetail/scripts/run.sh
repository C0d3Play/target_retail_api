docker build -f docker/myretail-dockerfile -t myretail:0.0.1 /libs
docker build -f docker/targetapi-dockerfile -t targetapi:0.0.1 /libs
docker-compose up --force-recreate