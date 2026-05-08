\c db_pagos

DROP TABLE IF EXISTS recibos;
DROP TABLE IF EXISTS transacciones;
DROP TABLE IF EXISTS pedidos_proyeccion;

CREATE TABLE pedidos_proyeccion (
    orden_id VARCHAR(20) PRIMARY KEY,
    monto_pendiente DECIMAL(12,2)
);

CREATE TABLE transacciones (
    id VARCHAR(50) PRIMARY KEY, -- ID de pasarela de pago (Stripe/PayPal)
    orden_id VARCHAR(20) REFERENCES pedidos_proyeccion(orden_id),
    metodo VARCHAR(20), -- 'CREDITO', 'DEBITO'
    estado VARCHAR(20) -- 'SUCCESS', 'FAILED'
);

CREATE TABLE recibos (
    id SERIAL PRIMARY KEY,
    transaccion_id VARCHAR(50) REFERENCES transacciones(id),
    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO pedidos_proyeccion VALUES ('ORD-001', 120.00);
INSERT INTO transacciones VALUES ('PAY-8822', 'ORD-001', 'CREDITO', 'SUCCESS');