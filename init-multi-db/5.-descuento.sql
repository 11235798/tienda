\c db_descuentos

DROP TABLE IF EXISTS cupones_uso;
DROP TABLE IF EXISTS cupones;
DROP TABLE IF EXISTS juegos_proyeccion;

CREATE TABLE juegos_proyeccion (
    sku VARCHAR(50) PRIMARY KEY,
    precio_base DECIMAL(10,2)
);

CREATE TABLE cupones (
    codigo VARCHAR(20) PRIMARY KEY,
    porcentaje_dcto INTEGER CHECK (porcentaje_dcto <= 100),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE cupones_uso (
    id SERIAL PRIMARY KEY,
    cupon_codigo VARCHAR(20) REFERENCES cupones(codigo),
    aplicado_a_sku VARCHAR(50) REFERENCES juegos_proyeccion(sku)
);

INSERT INTO juegos_proyeccion VALUES ('ELDER-001', 59.99);
INSERT INTO cupones VALUES ('OFFER20', 20, TRUE);