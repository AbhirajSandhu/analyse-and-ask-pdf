# Run Postgres Locally

cd local.dev/

## D O C K E R <br> G U I D E
<b>R U N</b> - <i>start the db server</i>  
docker compose up -d  

<b>S T O P</b> - <i>stop the db server</i>  
docker compose down

<b>L O G S</b> - <i>cmd for logs</i>  
docker compose logs postgres-vector

<b>R E F R E S H</b> - <i>refresh the volumes mounted through docker-compose</i>  
docker volume rm $(docker volume ls -q | grep postgres_vector_data)

<i>I will suggest to check docker app to view live logs and start/stop server.</i>

## D B <br> A C C E S S <br> G U I D E
<b>D A T A B A S E</b> - <i>access database from terminal</i>   
docker exec -it enterprise-vector-db psql -U my-local-dev -d vector_db   
docker exec -it enterprise-vector-db psql -U postgres -d vector_db  

<b>Q U I T</b> - <i>quit</i>  
\q

## D B <br> Q U E R I E S

### Access vector table 
<b><i>Count of the table</i></b>  
SELECT count(*) FROM vector_store;

<b><i>Access data in the table</i></b>  
SELECT  
    id,  
    left(content, 50) AS text_preview,  
    metadata->>'source_file_name' AS original_file,  
    metadata->>'ingested_by' AS author  
FROM vector_store  
LIMIT 3;  




<i>Alternatively you can download UI application for postgres.</i>  
