-- 1. Conexión
\c resenas

-- 2. Eliminación
DROP TABLE IF EXISTS opiniones;

-- 3. Creación
CREATE TABLE opiniones (
    id_opinion SERIAL PRIMARY KEY,
    id_producto INT NOT NULL,
    id_usuario INT NOT NULL,
    calificacion INT CHECK (calificacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha TIMESTAMP DEFAULT NOW()
);

-- 4. Datos de prueba
INSERT INTO opiniones (id_producto, id_usuario, calificacion, comentario) VALUES 
(1, 1, 5, 'Excelente producto!'),
(2, 2, 1, 'Llegó roto'), -- Caso borde: Calificación mínima
(3, 3, 3, NULL); -- Caso borde: Calificación sin texto

-- 5. Documentación: Almacena feedback de clientes; califica de 1 a 5 estrellas.