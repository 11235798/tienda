-- Usuarios
SELECT 'CREATE DATABASE usuarios'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'usuarios') \gexec

-- Catálogo de juegos
SELECT 'CREATE DATABASE catalogo'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'catalogo') \gexec

-- Inventario
SELECT 'CREATE DATABASE inventario'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'inventario') \gexec

-- Carrito de compras
SELECT 'CREATE DATABASE compras'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'compras') \gexec

-- Pagos
SELECT 'CREATE DATABASE pagos'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'pagos') \gexec

-- Pedidos
SELECT 'CREATE DATABASE pedidos'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'pedidos') \gexec

-- Recomendaciones
SELECT 'CREATE DATABASE recomendaciones'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'recomendaciones') \gexec

-- Reseñas
SELECT 'CREATE DATABASE resenas'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'resenas') \gexec

-- Notificaciones
SELECT 'CREATE DATABASE notificaciones'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'notificaciones') \gexec

-- Reseñas
SELECT 'CREATE DATABASE resenas'
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'resenas') \gexec