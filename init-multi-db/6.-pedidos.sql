-- 1. Conexión
\c pedidos

-- 2. Eliminación
DROP TABLE IF EXISTS detalle_pedidos;
DROP TABLE IF EXISTS ordenes;

-- 3. Creación
CREATE TABLE ordenes (
    id_orden SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'pendiente' CHECK (estado IN ('pendiente', 'pagado', 'enviado', 'cancelado')),
    total DECIMAL(10,2) DEFAULT 0
);

-- 4. Datos de prueba
INSERT INTO ordenes (id_usuario, estado, total) VALUES 
(1, 'pagado', 905.00),
(2, 'pendiente', 0.00), -- Caso borde: Pedido iniciado sin total aún
(3, 'cancelado', 50.00);

-- 5. Documentación: Registro principal de transacciones y estados de compra.