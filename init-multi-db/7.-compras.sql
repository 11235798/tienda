-- 1. Conexión
\c compras

-- 2. Eliminación
DROP TABLE IF EXISTS ordenes_compra;

-- 3. Creación
CREATE TABLE ordenes_compra (
    id_compra SERIAL PRIMARY KEY,
    proveedor VARCHAR(100) NOT NULL,
    monto_total DECIMAL(12,2) NOT NULL,
    fecha_recepcion DATE
);

-- 4. Datos de prueba
INSERT INTO ordenes_compra (proveedor, monto_total, fecha_recepcion) VALUES 
('Tech Corp', 50000.00, '2026-05-01'),
('Global Inc', 100.50, NULL), -- Caso borde: Pedido a proveedor no recibido
('Suministros X', 0.00, '2026-04-30'); -- Caso borde: Compra de costo cero (bonificación)

-- 5. Documentación: Registro de entrada de mercancía desde proveedores externos.