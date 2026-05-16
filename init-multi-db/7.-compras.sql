\c db_compras

DROP TABLE IF EXISTS ordenes_compra;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS juegos_proyeccion;

CREATE TABLE juegos_proyeccion (
    sku     VARCHAR(50) UNIQUE  PRIMARY KEY,
    titulo  VARCHAR(150)
);

CREATE TABLE proveedores (
    rut             VARCHAR(20)     UNIQUE PRIMARY KEY,
    razon_social    VARCHAR(150)    NOT NULL,
    contacto        VARCHAR(100)
);

CREATE TABLE ordenes_compra (
    id              SERIAL      PRIMARY KEY,
    proveedor_rut   VARCHAR(20) REFERENCES proveedores(rut) NOT NULL,
    juego_sku       VARCHAR(50) REFERENCES juegos_proyeccion(sku) NOT NULL,
    costo_unitario  INT         NOT NULL,
    fecha_entrega   DATE        NOT NULL
);

INSERT INTO proveedores VALUES ('77-888-9', 'Sony Latam', 'ventas@sony.com');
INSERT INTO juegos_proyeccion VALUES ('ELDER-001', 'Elden Ring');
INSERT INTO ordenes_compra (proveedor_rut, juego_sku, costo_unitario, fecha_entrega) VALUES ('77-888-9', 'ELDER-001', 60000, "2026-07-31") 