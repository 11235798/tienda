-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- 1. Conexión
\c usuarios

-- 2. Eliminación (Jerarquía inversa)
DROP TABLE IF EXISTS perfiles;
DROP TABLE IF EXISTS cuentas;

-- 3. Creación con restricciones
CREATE TABLE cuentas (
    id_usuario SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    password_hash TEXT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_email ON cuentas(email);

-- 4. Datos de prueba
INSERT INTO cuentas (email, password_hash) VALUES 
('admin@tienda.com', 'hash_seguro_1'), -- Caso normal
('usuario_borde@test.com', 'hash_seguro_2'),
('t@t.co', 'hash_minimo'); -- Caso borde (email corto)

-- 5. Documentación: Almacena credenciales y asegura emails únicos y válidos.