-- 1. Conexión
\c pagos

-- 2. Eliminación
DROP TABLE IF EXISTS transacciones;

-- 3. Creación
CREATE TABLE transacciones (
    id_transaccion UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_orden INT NOT NULL,
    metodo VARCHAR(50) CHECK (metodo IN ('tarjeta', 'transferencia', 'crypto')),
    monto DECIMAL(10,2) NOT NULL,
    exitoso BOOLEAN DEFAULT FALSE
);

-- 4. Datos de prueba
INSERT INTO transacciones (id_orden, metodo, monto, exitoso) VALUES 
(1, 'tarjeta', 905.00, TRUE),
(1, 'tarjeta', 905.00, FALSE), -- Caso borde: Intento fallido
(2, 'crypto', 0.01, TRUE); -- Caso borde: Monto cripto muy pequeño

-- 5. Documentación: Auditoría de intentos de pago y métodos utilizados.