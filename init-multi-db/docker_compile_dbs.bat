docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 01-init.sql
docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 2.-usuarios.sql
docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 3.-catalogo.sql
docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 5.-descuento.sql
docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 7.-compras.sql
docker exec -i postgres_rdbms psql -U postgres -d main_postgres_db < 9.resenas.sql


