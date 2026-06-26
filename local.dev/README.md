# Run Postgres Locally

## D O C K E R <br> G U I D E
<b>R U N</b> - <i>start the db server</i>  
docker compose up -d  

<b>S T O P</b> - <i>stop the db server</i>  
docker compose down

<b>L O G S</b> - <i>cmd for logs</i>  
docker compose logs postgres-vector

<b>R E F R E S H</b> - <i>refresh the volumes mounted through docker-compose</i>  
docker volume rm $(docker volume ls -q | grep postgres_vector_data)

<i>I will suggest to download docker app to view live logs and start/stop server.</i>

## D B <br> A C C E S S <br> G U I D E
<b>D A T A B A S E</b> - <i>access database from terminal</i>  
docker exec -it enterprise-vector-db psql -U my-local-dev -d vector_db 

<b>Q U I T</b> - <i>quit</i>  
\q

<i>Alternatively you can download UI application for postgres.</i>  
