SELECT 'CREATE DATABASE usuarios'           WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'usuarios')         \gexec
SELECT 'CREATE DATABASE catalogo'           WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'catalogo')         \gexec
SELECT 'CREATE DATABASE compras'            WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'compras')          \gexec
SELECT 'CREATE DATABASE resenas'            WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'resenas')          \gexec
SELECT 'CREATE DATABASE descuentos'         WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'descuentos')       \gexec