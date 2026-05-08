\c db_pedidos

DROP TABLE IF EXISTS items_pedido;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS usuarios_proyeccion;

CREATE TABLE usuarios_proyeccion (
    email VARCHAR(100) PRIMARY KEY,
    nombre VARCHAR(150)
);

CREATE TABLE pedidos (
    orden_id VARCHAR(20) PRIMARY KEY,
    user_email VARCHAR(100) REFERENCES usuarios_proyeccion(email),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12,2)
);

CREATE TABLE items_pedido (
    id SERIAL PRIMARY KEY,
    orden_id VARCHAR(20) REFERENCES pedidos(orden_id),
    juego_sku VARCHAR(50), -- Se guarda el SKU directamente
    cantidad INTEGER
);

INSERT INTO usuarios_proyeccion VALUES ('gamer99@gmail.com', 'Juan Perez');
INSERT INTO pedidos VALUES ('ORD-001', 'gamer99@gmail.com', NOW(), 120.00);