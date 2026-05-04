docker exec -i postgres-db psql -U postgres -d postgres < 01-init.sql
docker exec -i postgres-db psql -U postgres -d postgres < 02-create_usuarios.sql
docker exec -i postgres-db psql -U postgres -d postgres < 03-create_catalogo.sql
