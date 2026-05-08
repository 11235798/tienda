\c db_catalogo

DROP TABLE IF EXISTS juego_categoria;
DROP TABLE IF EXISTS juegos;
DROP TABLE IF EXISTS categorias;

CREATE TABLE categorias (
    slug VARCHAR(50) PRIMARY KEY, -- Clave alterna para proyecciones
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE juegos (
    sku VARCHAR(50) PRIMARY KEY, -- Stock Keeping Unit como ID de negocio
    titulo VARCHAR(150) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    consola VARCHAR(50) NOT NULL
);

CREATE TABLE juego_categoria (
    juego_sku VARCHAR(50) REFERENCES juegos(sku),
    categoria_slug VARCHAR(50) REFERENCES categorias(slug),
    PRIMARY KEY (juego_sku, categoria_slug)
);

-- Datos de prueba
INSERT INTO categorias VALUES ('rpg', 'Role-Playing Games'), ('action', 'Acción');
INSERT INTO juegos VALUES ('ELDER-001', 'Elden Ring', 59.99, 'PS5'), ('COD-2024', 'Call of Duty', 69.99, 'PC');
INSERT INTO juego_categoria VALUES ('ELDER-001', 'rpg'), ('ELDER-001', 'action');
