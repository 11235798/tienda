-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS
-- 1. Conexión
\c catalogo

-- 2. Eliminación
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias;

-- 3. Creación
CREATE TABLE categorias (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE productos (
    id_producto SERIAL PRIMARY KEY,
    id_categoria INT REFERENCES categorias(id_categoria),
    nombre VARCHAR(200) NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    activo BOOLEAN DEFAULT TRUE
);

-- 4. Datos de prueba
INSERT INTO categorias (nombre) VALUES ('Electrónica'), ('Hogar');
INSERT INTO productos (id_categoria, nombre, precio) VALUES 
(1, 'Smartphone X', 899.99),
(2, 'Taza Cerámica', 5.00),
(1, 'Cable USB-C', 0.99); -- Caso borde: Precio muy bajo

-- 5. Documentación: Listado de productos con categorías y validación de precio positivo.
