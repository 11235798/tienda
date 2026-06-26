\c resenas;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS resenas;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS usuarios;

-- 2. TABLAS MAESTRAS
CREATE TABLE usuarios (
    id                SERIAL       PRIMARY KEY,
    nickname          VARCHAR(100) UNIQUE NOT NULL,
    email             VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE videojuegos (
    id                SERIAL       PRIMARY KEY,
    sku               VARCHAR(20)  UNIQUE NOT NULL,
    titulo            VARCHAR(255) NOT NULL,
    desarrolladora    VARCHAR(100) NOT NULL,
    anio_lanzamiento  INT          NOT NULL CHECK (anio_lanzamiento BETWEEN 1950 AND 2100)
);

-- 3. TABLA INTERMEDIA (CON CARGA DE DATOS)
CREATE TABLE resenas (
    id                SERIAL       PRIMARY KEY,
    videojuego_id     INT          NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    usuario_id        INT          NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    calificacion      INT          NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
    comentario        TEXT,
    UNIQUE (videojuego_id, usuario_id) -- Regla: Un usuario solo puede dejar una reseña por juego
);

-- 4. ÍNDICES DE RENDIMIENTO
CREATE INDEX idx_videojuegos_sku ON videojuegos(sku);
CREATE INDEX idx_usuarios_nickname ON usuarios(nickname);
CREATE INDEX idx_resenas_videojuego ON resenas(videojuego_id);
CREATE INDEX idx_resenas_usuario ON resenas(usuario_id);

-- 5. DATOS DE PRUEBA
INSERT INTO usuarios (nickname, email) VALUES
('GamerPro99', 'pro99@email.com'), 
('CasualPlayer', 'casual@email.com'), 
('RetroFan', 'retro@email.com'), 
('SpeedRunner', 'speed@email.com');

INSERT INTO videojuegos (sku, titulo, desarrolladora, anio_lanzamiento) VALUES
('VG-9001', 'Free Fire',                  'Garena',          2017),
('VG-9002', 'Fortnite',                   'Epic Games',      2017),
('VG-9003', 'Geometry Dash',              'RobTop Games',    2013),
('VG-9004', 'Assassin''s Creed Valhalla', 'Ubisoft',         2020);

INSERT INTO resenas (videojuego_id, usuario_id, calificacion, comentario) VALUES
-- Reseñas de Free Fire
(1, 1, 4, 'Muy buen battle royale para móviles, corre fluido.'),
(1, 2, 3, 'Divertido para jugar con amigos, pero a veces hay lag.'),

-- Reseñas de Fortnite
(2, 1, 5, 'Las mecánicas y las colaboraciones son geniales.'),
(2, 4, 4, 'Excelente rendimiento, aunque el meta cambia demasiado rápido.'),

-- Reseñas de Geometry Dash
(3, 3, 5, 'Un clásico. La música y la dificultad son perfectas para estar horas intentándolo.'),
(3, 4, 5, 'Los niveles creados por la comunidad le dan rejugabilidad infinita.'),

-- Reseñas de Assassin's Creed Valhalla
(4, 2, 4, 'Mundo abierto inmenso y gráficamente increíble.'),
(4, 3, 2, 'Demasiado largo y se siente muy repetitivo después de unas horas de campaña.');