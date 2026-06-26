\c recomendaciones;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS recomendaciones;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS usuarios;

-- 2. TABLAS MAESTRAS
CREATE TABLE usuarios (
    id                SERIAL       PRIMARY KEY,
    nickname          VARCHAR(100) UNIQUE NOT NULL,
    perfil_jugador    VARCHAR(50)  NOT NULL -- Ej: 'Móvil', 'Competitivo', 'Casual'
);

CREATE TABLE videojuegos (
    id                SERIAL       PRIMARY KEY,
    sku               VARCHAR(20)  UNIQUE NOT NULL,
    titulo            VARCHAR(255) NOT NULL,
    genero_principal  VARCHAR(100) NOT NULL,
    plataforma        VARCHAR(100) NOT NULL
);

-- 3. TABLA INTERMEDIA (SISTEMA DE RECOMENDACIÓN)
CREATE TABLE recomendaciones (
    id                  SERIAL       PRIMARY KEY,
    usuario_id          INT          NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    videojuego_id       INT          NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    porcentaje_afinidad DECIMAL(5,2) NOT NULL CHECK (porcentaje_afinidad BETWEEN 0 AND 100),
    estado              VARCHAR(20)  DEFAULT 'Pendiente' CHECK (estado IN ('Pendiente', 'Aceptada', 'Ignorada')),
    UNIQUE (usuario_id, videojuego_id) -- Regla: No recomendar exactamente el mismo juego al mismo usuario dos veces
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para un motor de recomendaciones rápido)
CREATE INDEX idx_recomendaciones_usuario ON recomendaciones(usuario_id);
CREATE INDEX idx_recomendaciones_videojuego ON recomendaciones(videojuego_id);
CREATE INDEX idx_recomendaciones_afinidad ON recomendaciones(porcentaje_afinidad DESC);

-- 5. DATOS DE PRUEBA
INSERT INTO usuarios (nickname, perfil_jugador) VALUES
('Alex_Dev', 'Competitivo Móvil'),
('RhythmMaster', 'Casual PC / Móvil'),
('Survivor101', 'Multiplataforma');

INSERT INTO videojuegos (sku, titulo, genero_principal, plataforma) VALUES
('VG-9001', 'Free Fire',              'Battle Royale', 'Móvil'),
('VG-9002', 'Fortnite',               'Battle Royale', 'Multiplataforma'),
('VG-9003', 'Geometry Dash',          'Plataformas',   'Móvil / PC'),
('VG-9004', 'PUBG Mobile',            'Battle Royale', 'Móvil'),
('VG-9005', 'Osu!',                   'Ritmo',         'PC'),
('VG-9006', 'Hungry Shark Evolution', 'Acción',        'Móvil');

INSERT INTO recomendaciones (usuario_id, videojuego_id, porcentaje_afinidad, estado) VALUES
-- Recomendaciones generadas para Alex_Dev (Perfil enfocado en acción y móvil)
(1, 4, 95.50, 'Aceptada'),  -- Alta afinidad por jugar otros Battle Royale
(1, 6, 82.10, 'Pendiente'), -- Sugerencia de acción casual en móvil
(1, 2, 78.00, 'Ignorada'),  -- Afinidad media/alta, pero el usuario la descartó

-- Recomendaciones generadas para RhythmMaster (Perfil enfocado en ritmo y plataformas)
(2, 5, 98.30, 'Aceptada'),  -- Sugerencia directa basada en el género musical
(2, 3, 85.00, 'Pendiente'), -- Fuerte recomendación multiplataforma

-- Recomendaciones generadas para Survivor101
(3, 2, 75.00, 'Pendiente');