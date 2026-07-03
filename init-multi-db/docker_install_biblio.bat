docker run --name postgres-rdbms -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=main_postgres_db -p 5433:5432 -d postgres

: según Gemini (opción 2):
: docker run --name postgres-rdbms \
:   -e POSTGRES_USER=postgres \
:   -e POSTGRES_PASSWORD=123 \
:   -e POSTGRES_DB=main_postgres_db \
:   -v C:\tienda\init-multi-db:/docker-entrypoint-initdb.d \
:   -p 5433:5432 \
:   -d postgres