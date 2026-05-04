-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
-- 1. Conexión
\c inventario

-- 2. Eliminación
DROP TABLE IF EXISTS stock;

-- 3. Creación
CREATE TABLE stock (
    id_producto INT PRIMARY KEY,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    ubicacion_pasillo VARCHAR(50),
    ultima_actualizacion TIMESTAMP DEFAULT NOW()
);

-- 4. Datos de prueba
INSERT INTO stock (id_producto, cantidad, ubicacion_pasillo) VALUES 
(1, 100, 'A-12'),
(2, 0, 'B-05'), -- Caso borde: Sin stock
(3, 9999, 'C-01'); -- Caso borde: Stock masivo

-- 5. Documentación: Rastreo de unidades físicas por producto; impide stock negativo.