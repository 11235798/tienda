\c db_descuentos;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS videojuego_descuento;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS campanas_descuento;

-- 2. TABLAS MAESTRAS
CREATE TABLE campanas_descuento (
    id                  SERIAL       PRIMARY KEY,
    codigo_promocion    VARCHAR(50)  UNIQUE NOT NULL,
    nombre_campana      VARCHAR(100) NOT NULL,
    porcentaje_rebaja   INT          NOT NULL CHECK (porcentaje_rebaja > 0 AND porcentaje_rebaja <= 100),
    fecha_inicio        TIMESTAMP    NOT NULL,
    fecha_fin           TIMESTAMP    NOT NULL,
    CHECK (fecha_fin > fecha_inicio)
);

CREATE TABLE videojuegos (
    id                  SERIAL       PRIMARY KEY,
    sku                 VARCHAR(20)  UNIQUE NOT NULL,
    titulo              VARCHAR(255) NOT NULL,
    precio_base         INT          NOT NULL CHECK (precio_base >= 0)
);

-- 3. TABLA INTERMEDIA (ASIGNACIÓN DE PROMOCIONES)
CREATE TABLE videojuego_descuento (
    id                  SERIAL       PRIMARY KEY,
    videojuego_id       INT          NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    campana_id          INT          NOT NULL REFERENCES campanas_descuento(id) ON DELETE CASCADE,
    estado              VARCHAR(20)  DEFAULT 'Activo' CHECK (estado IN ('Activo', 'Pausado')),
    UNIQUE (videojuego_id, campana_id) -- Regla: Un juego no puede estar dos veces en la misma campaña
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para calcular precios en tiempo real)
CREATE INDEX idx_campanas_fechas ON campanas_descuento(fecha_inicio, fecha_fin);
CREATE INDEX idx_descuentos_videojuego ON videojuego_descuento(videojuego_id);
CREATE INDEX idx_descuentos_campana ON videojuego_descuento(campana_id);

-- 5. DATOS DE PRUEBA
INSERT INTO campanas_descuento (codigo_promocion, nombre_campana, porcentaje_rebaja, fecha_inicio, fecha_fin) VALUES
('SUMMER2026',  'Ofertas de Verano',         50, '2026-01-01 00:00:00', '2026-02-28 23:59:59'),
('HALLOWEEN26', 'Especial de Terror',        30, '2026-10-25 00:00:00', '2026-11-02 23:59:59'),
('WEEKEND-PUB', 'Fin de Semana de Publisher', 15, '2026-05-15 00:00:00', '2026-05-18 23:59:59');

INSERT INTO videojuegos (sku, titulo, precio_base) VALUES
('VG-001', 'Resident Evil 4 Remake', 40000),
('VG-002', 'Silent Hill 2',          45000),
('VG-003', 'EA Sports FC 24',        50000),
('VG-004', 'The Witcher 3',          20000),
('VG-005', 'Hollow Knight',          10000);

INSERT INTO videojuego_descuento (videojuego_id, campana_id, estado) VALUES
-- Juegos en la oferta de Verano (50% de descuento)
(3, 1, 'Activo'), 
(4, 1, 'Activo'), 
(5, 1, 'Activo'),

-- Juegos en el especial de Terror (30% de descuento)
(1, 2, 'Activo'),
(2, 2, 'Activo'),

-- The Witcher 3 también entra en el fin de semana de publisher, pero la promo fue pausada
(4, 3, 'Pausado');