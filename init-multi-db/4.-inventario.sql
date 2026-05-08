\c db_inventario

DROP TABLE IF EXISTS movimientos;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS juegos_proyeccion;

-- Proyección necesaria para saber qué producto estamos inventariando
CREATE TABLE juegos_proyeccion (
    sku VARCHAR(50) PRIMARY KEY,
    titulo VARCHAR(150)
);

CREATE TABLE stock (
    juego_sku VARCHAR(50) PRIMARY KEY REFERENCES juegos_proyeccion(sku),
    cantidad_disponible INTEGER NOT NULL DEFAULT 0,
    ubicacion_bodega VARCHAR(50)
);

CREATE TABLE movimientos (
    id SERIAL PRIMARY KEY,
    juego_sku VARCHAR(50) REFERENCES stock(juego_sku),
    tipo_movimiento VARCHAR(10), -- 'IN', 'OUT'
    cantidad INTEGER NOT NULL
);

-- Datos de prueba
INSERT INTO juegos_proyeccion VALUES ('ELDER-001', 'Elden Ring');
INSERT INTO stock VALUES ('ELDER-001', 50, 'Pasillo A-1');