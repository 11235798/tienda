\c db_usuarios

DROP TABLE IF EXISTS perfiles;
DROP TABLE IF EXISTS usuarios;

-- Tabla principal de usuarios
CREATE TABLE usuarios (
    email VARCHAR(100) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de perfiles vinculada al usuario
CREATE TABLE perfiles (
    id SERIAL PRIMARY KEY,
    user_email VARCHAR(100) REFERENCES usuarios(email) ON DELETE CASCADE,
    nombre_completo VARCHAR(150),
    pais VARCHAR(50),
    suscripcion_tipo VARCHAR(20) -- 'BASIC', 'PREMIUM'
);

-- Datos de prueba
INSERT INTO usuarios (email, username, password_hash) VALUES 
('admin@tienda.com', 'admin_boss', 'hash_123'),
('gamer99@gmail.com', 'dark_knight', 'hash_456');

INSERT INTO perfiles (user_email, nombre_completo, pais, suscripcion_tipo) VALUES 
('admin@tienda.com', 'System Admin', 'Chile', 'PREMIUM'),
('gamer99@gmail.com', 'Juan Perez', 'Mexico', 'BASIC');