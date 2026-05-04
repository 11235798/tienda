-- 1. Conexión
\c descuentos

-- 2. Eliminación
DROP TABLE IF EXISTS cupones;

-- 3. Creación
CREATE TABLE cupones (
    codigo VARCHAR(20) PRIMARY KEY,
    porcentaje INT CHECK (porcentaje BETWEEN 1 AND 100),
    fecha_expiracion DATE NOT NULL,
    uso_maximo INT DEFAULT 1
);

-- 4. Datos de prueba
INSERT INTO cupones (codigo, porcentaje, fecha_expiracion, uso_maximo) VALUES 
('BIENVENIDA10', 10, '2026-12-31', 1000),
('REVENTON100', 100, '2026-01-01', 1), -- Caso borde: Descuento total
('MINI', 1, '2026-05-01', 5); -- Caso borde: Descuento mínimo

-- 5. Documentación: Gestiona códigos promocionales con límites de uso y tiempo.