\c catalogo;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS videojuego_categoria;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS categorias;

-- 2. TABLAS MAESTRAS
CREATE TABLE categorias (
    id                SERIAL       PRIMARY KEY,
    nombre            VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE videojuegos (
    id                SERIAL       PRIMARY KEY,
    sku               VARCHAR(20)  UNIQUE NOT NULL,
    titulo            VARCHAR(255) NOT NULL,
    desarrolladora    VARCHAR(100) NOT NULL,
    anio_lanzamiento  INT          NOT NULL CHECK (anio_lanzamiento BETWEEN 1950 AND 2100),
    plataforma        VARCHAR(150) NOT NULL
);

CREATE TABLE videojuego_categoria (
    id                SERIAL       PRIMARY KEY,
    videojuego_id     INT          NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    categoria_id      INT          NOT NULL REFERENCES categorias(id) ON DELETE RESTRICT,
    UNIQUE (videojuego_id, categoria_id)
);

CREATE INDEX idx_videojuegos_sku ON videojuegos(sku);
CREATE INDEX idx_videojuegos_titulo ON videojuegos(titulo);
CREATE INDEX idx_videojuego_categoria_videojuego ON videojuego_categoria(videojuego_id);
CREATE INDEX idx_videojuego_categoria_categoria ON videojuego_categoria(categoria_id);

-- 3. DATOS DE PRUEBA
INSERT INTO categorias (nombre) VALUES
('Battle Royale'), ('Plataformas'), ('Ritmo'), ('Supervivencia'), ('Aventura'), ('Acción'), ('RPG'), ('Deportes');

INSERT INTO videojuegos (sku, titulo, desarrolladora, anio_lanzamiento, plataforma) VALUES
('VG-9001', 'Free Fire',                  'Garena',          2017, 'Móvil'),
('VG-9002', 'Fortnite',                   'Epic Games',      2017, 'Multiplataforma'),
('VG-9003', 'Geometry Dash',              'RobTop Games',    2013, 'Móvil / PC'),
('VG-9004', 'Hungry Shark Evolution',     'Ubisoft',         2012, 'Móvil'),
('VG-9005', 'Assassin''s Creed Valhalla', 'Ubisoft',         2020, 'Multiplataforma'),
('VG-9006', 'The Legend of Zelda: BOTW',  'Nintendo',        2017, 'Nintendo Switch'),
('VG-9007', 'EA Sports FC 24',            'Electronic Arts', 2023, 'Multiplataforma'),
('VG-9008', 'Minecraft',                  'Mojang',          2011, 'Multiplataforma'),
('VG-9009', 'Elden Ring',                 'FromSoftware',    2022, 'Multiplataforma');

INSERT INTO videojuego_categoria (videojuego_id, categoria_id) VALUES
-- Free Fire: Battle Royale, Supervivencia, Acción
(1, 1), (1, 4), (1, 6), 
-- Fortnite: Battle Royale, Acción
(2, 1), (2, 6), 
-- Geometry Dash: Plataformas, Ritmo
(3, 2), (3, 3), 
-- Hungry Shark Evolution: Acción, Aventura
(4, 6), (4, 5),
-- Assassin's Creed Valhalla: Aventura, RPG, Acción
(5, 5), (5, 7), (5, 6),
-- The Legend of Zelda: BOTW: Aventura, RPG
(6, 5), (6, 7), 
-- EA Sports FC 24: Deportes
(7, 8), 
-- Minecraft: Supervivencia, Aventura
(8, 4), (8, 5), 
-- Elden Ring: RPG, Acción
(9, 7), (9, 6);