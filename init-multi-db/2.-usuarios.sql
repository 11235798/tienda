\c db_usuarios;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS usuario_logros;
DROP TABLE IF EXISTS logros;
DROP TABLE IF EXISTS usuarios;

-- 2. TABLAS MAESTRAS
CREATE TABLE usuarios (
    id                  SERIAL       PRIMARY KEY,
    nickname            VARCHAR(100) UNIQUE NOT NULL,
    email               VARCHAR(150) UNIQUE NOT NULL,
    nivel_cuenta        INT          DEFAULT 1 CHECK (nivel_cuenta > 0),
    fecha_registro      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE logros (
    id                  SERIAL       PRIMARY KEY,
    codigo_logro        VARCHAR(50)  UNIQUE NOT NULL,
    juego_asociado      VARCHAR(100) NOT NULL,
    titulo              VARCHAR(150) NOT NULL,
    rareza              VARCHAR(50)  NOT NULL CHECK (rareza IN ('Común', 'Raro', 'Épico', 'Legendario')),
    xp_otorgada         INT          NOT NULL CHECK (xp_otorgada >= 0)
);

-- 3. TABLA INTERMEDIA (REGISTRO DE DESBLOQUEOS)
CREATE TABLE usuario_logros (
    id                  SERIAL       PRIMARY KEY,
    usuario_id          INT          NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    logro_id            INT          NOT NULL REFERENCES logros(id) ON DELETE CASCADE,
    fecha_desbloqueo    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (usuario_id, logro_id) -- Regla: Un usuario solo puede desbloquear un logro específico una vez
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para cargar el perfil del jugador rápido)
CREATE INDEX idx_usuarios_nickname ON usuarios(nickname);
CREATE INDEX idx_logros_juego ON logros(juego_asociado);
CREATE INDEX idx_desbloqueos_usuario ON usuario_logros(usuario_id);
CREATE INDEX idx_desbloqueos_fecha ON usuario_logros(fecha_desbloqueo DESC);

-- 5. DATOS DE PRUEBA
INSERT INTO usuarios (nickname, email, nivel_cuenta) VALUES
('Alex_Pro',     'alex@email.com',   42),
('SniperWolf',   'sniper@email.com', 15),
('RhythmGod',    'rhythm@email.com', 89);

INSERT INTO logros (codigo_logro, juego_asociado, titulo, rareza, xp_otorgada) VALUES
('FF-BOOYAH-01', 'Free Fire',      'Primer Booyah!',             'Común',     500),
('FF-HEAD-100',  'Free Fire',      'Tirador de Élite (100 HS)',  'Épico',     2500),
('FN-CROWN-10',  'Fortnite',       'Corona de la Victoria',      'Raro',      1000),
('GD-DEMON-01',  'Geometry Dash',  'Cazador de Demonios',        'Legendario', 5000),
('GD-JUMP-999',  'Geometry Dash',  'Salto de Fe (1000 saltos)',  'Común',     200);

INSERT INTO usuario_logros (usuario_id, logro_id) VALUES
-- Alex_Pro desbloquea logros en juegos móviles y battle royales
(1, 1), -- Primer Booyah en Free Fire
(1, 2), -- 100 Headshots en Free Fire
(1, 4), -- Pasó su primer nivel Demon en Geometry Dash

-- SniperWolf se enfoca en Fortnite y tiros a la cabeza
(2, 3), -- Corona en Fortnite
(2, 2), -- 100 Headshots en Free Fire (Juegan ambos)

-- RhythmGod se especializa en juegos de ritmo y plataformas
(3, 4), -- Nivel Demon en Geometry Dash
(3, 5); -- 1000 saltos en Geometry Dash