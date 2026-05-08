\c db_compras

DROP TABLE IF EXISTS ordenes_compra;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS juegos_proyeccion;

CREATE TABLE juegos_proyeccion (
    sku VARCHAR(50) PRIMARY KEY,
    titulo VARCHAR(150)
);

CREATE TABLE proveedores (
    rut VARCHAR(20) PRIMARY KEY,
    razon_social VARCHAR(150) NOT NULL,
    contacto VARCHAR(100)
);

CREATE TABLE ordenes_compra (
    id SERIAL PRIMARY KEY,
    proveedor_rut VARCHAR(20) REFERENCES proveedores(rut),
    juego_sku VARCHAR(50) REFERENCES juegos_proyeccion(sku),
    costo_unitario DECIMAL(10,2),
    fecha_entrega DATE
);

INSERT INTO proveedores VALUES ('77-888-9', 'Sony Latam', 'ventas@sony.com');
INSERT INTO juegos_proyeccion VALUES ('ELDER-001', 'Elden Ring');