-- 1. Conexión
\c recomendaciones

-- 2. Eliminación
DROP TABLE IF EXISTS sugerencias;

-- 3. Creación
CREATE TABLE sugerencias (
    id_usuario INT,
    id_producto_recomendado INT,
    puntuacion_relevancia DECIMAL(3,2), -- 0.00 a 1.00
    PRIMARY KEY (id_usuario, id_producto_recomendado)
);

-- 4. Datos de prueba
INSERT INTO sugerencias VALUES 
(1, 10, 0.95),
(1, 11, 0.80),
(2, 1, 0.01); -- Caso borde: Relevancia casi nula

-- 5. Documentación: Motor de sugerencias que vincula usuarios con productos potenciales.