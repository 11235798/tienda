docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/01-init.sql
docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/2.-usuarios.sql
docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/3.-catalogo.sql
docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/5.-descuento.sql
docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/7.-compras.sql
docker exec postgres-rdbms psql -U postgres -d main_postgres_db -f /docker-entrypoint-initdb.d/9.resenas.sql
