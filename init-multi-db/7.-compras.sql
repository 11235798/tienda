\c db_compras;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS detalle_compras;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS clientes;

-- 2. TABLAS MAESTRAS
CREATE TABLE clientes (
    id                SERIAL       PRIMARY KEY,
    rut               VARCHAR(15)  UNIQUE NOT NULL,
    nombre            VARCHAR(100) NOT NULL,
    email             VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE videojuegos (
    id                SERIAL       PRIMARY KEY,
    sku               VARCHAR(20)  UNIQUE NOT NULL,
    titulo            VARCHAR(255) NOT NULL,
    formato           VARCHAR(50)  NOT NULL CHECK (formato IN ('Físico', 'Digital')),
    precio_actual     INT          NOT NULL CHECK (precio_actual >= 0)
);

-- 3. TABLA INTERMEDIA (REGISTRO DE ÓRDENES / COMPRAS)
CREATE TABLE detalle_compras (
    id                SERIAL       PRIMARY KEY,
    cliente_id        INT          NOT NULL REFERENCES clientes(id) ON DELETE RESTRICT,
    videojuego_id     INT          NOT NULL REFERENCES videojuegos(id) ON DELETE RESTRICT,
    cantidad          INT          NOT NULL CHECK (cantidad > 0),
    precio_historico  INT          NOT NULL CHECK (precio_historico >= 0), -- Congela el precio al momento de comprar
    fecha_compra      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
    -- Nota: Tampoco hay UNIQUE(cliente_id, videojuego_id) porque un cliente puede volver a comprar el mismo juego (ej. para regalo).
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para el historial de compras del usuario)
CREATE INDEX idx_videojuegos_sku ON videojuegos(sku);
CREATE INDEX idx_compras_cliente ON detalle_compras(cliente_id);
CREATE INDEX idx_compras_videojuego ON detalle_compras(videojuego_id);
CREATE INDEX idx_compras_fecha ON detalle_compras(fecha_compra);

-- 5. DATOS DE PRUEBA
INSERT INTO clientes (rut, nombre, email) VALUES
('11222333-4', 'Carlos Gamer', 'carlos@email.com'), 
('22333444-5', 'Ana Player',   'ana@email.com'), 
('33444555-6', 'Luis Retro',   'luis@email.com');

INSERT INTO videojuegos (sku, titulo, formato, precio_actual) VALUES
('VG-FIS-001', 'The Legend of Zelda: BOTW',  'Físico',  45000),
('VG-FIS-002', 'EA Sports FC 24',            'Físico',  50000),
('VG-DIG-001', 'Minecraft',                  'Digital', 25000),
('VG-DIG-002', 'Elden Ring',                 'Digital', 40000),
('VG-DIG-003', 'Geometry Dash',              'Digital', 3500);

INSERT INTO detalle_compras (cliente_id, videojuego_id, cantidad, precio_historico) VALUES
-- Carlos compró dos juegos digitales
(1, 3, 1, 25000),
(1, 4, 1, 40000),

-- Ana compró un juego físico y uno digital (El precio histórico coincide con el actual)
(2, 1, 1, 45000),
(2, 5, 1, 3500),

-- Luis compró dos copias del mismo juego físico (ej. para él y para regalar)
(3, 2, 2, 50000),

-- Carlos volvió a comprar un juego que estaba en oferta en ese momento (precio histórico es menor al actual)
(1, 5, 1, 1500);