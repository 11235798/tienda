-- Usuarios
SELECT 'CREATE DATABASE db_usuarios' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_usuarios') \gexec

-- Catalogo de juegos
SELECT 'CREATE DATABASE db_catalogo' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_catalogo') \gexec

-- Inventario
SELECT 'CREATE DATABASE db_inventario' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_inventario') \gexec

-- Carrito de compras
SELECT 'CREATE DATABASE db_compras' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_compras') \gexec

-- Pagos
SELECT 'CREATE DATABASE db_pagos' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_pagos') \gexec

-- Pedidos
SELECT 'CREATE DATABASE db_pedidos' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_pedidos') \gexec

-- Recomendaciones
SELECT 'CREATE DATABASE db_recomendaciones' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_recomendaciones') \gexec

-- Reseñas
SELECT 'CREATE DATABASE db_resenas' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_resenas') \gexec

-- Notificaciones
SELECT 'CREATE DATABASE db_notificaciones' WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'db_notificaciones') \gexec
