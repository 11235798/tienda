\c inventario;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS inventario_bodegas;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS bodegas;

-- 2. TABLAS MAESTRAS
CREATE TABLE bodegas (
    id                  SERIAL       PRIMARY KEY,
    codigo_bodega       VARCHAR(20)  UNIQUE NOT NULL,
    nombre              VARCHAR(100) NOT NULL,
    direccion           VARCHAR(255) NOT NULL,
    tipo_almacenamiento VARCHAR(50)  NOT NULL -- Ej: 'Principal', 'Tienda', 'Devoluciones'
);

CREATE TABLE videojuegos (
    id                  SERIAL       PRIMARY KEY,
    sku                 VARCHAR(20)  UNIQUE NOT NULL,
    titulo              VARCHAR(255) NOT NULL,
    plataforma          VARCHAR(100) NOT NULL,
    formato             VARCHAR(50)  NOT NULL CHECK (formato IN ('Físico', 'Digital'))
    -- Nota: En sistemas de inventario reales, a veces se controlan tarjetas físicas con códigos digitales.
);

-- 3. TABLA INTERMEDIA (CONTROL DE STOCK POR UBICACIÓN)
CREATE TABLE inventario_bodegas (
    id                  SERIAL       PRIMARY KEY,
    bodega_id           INT          NOT NULL REFERENCES bodegas(id) ON DELETE CASCADE,
    videojuego_id       INT          NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    stock_actual        INT          NOT NULL CHECK (stock_actual >= 0),
    stock_minimo        INT          NOT NULL CHECK (stock_minimo >= 0),
    ultima_revision     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (bodega_id, videojuego_id) -- Regla: Un juego tiene un solo registro de stock activo por cada bodega
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para reportes de reabastecimiento y auditorías)
CREATE INDEX idx_inventario_bodega ON inventario_bodegas(bodega_id);
CREATE INDEX idx_inventario_videojuego ON inventario_bodegas(videojuego_id);
CREATE INDEX idx_inventario_bajo_stock ON inventario_bodegas(stock_actual);

-- 5. DATOS DE PRUEBA
INSERT INTO bodegas (codigo_bodega, nombre, direccion, tipo_almacenamiento) VALUES
('BOD-CENTRAL', 'Bodega Central Distribución', 'Av. Américo Vespucio 123', 'Principal'),
('SUC-CENTRO',  'Tienda Centro Ciudad',        'Ahumada 456',              'Tienda'),
('SUC-MALL',    'Tienda Mall Plaza',           'Av. Vicuña Mackenna 789',  'Tienda');

INSERT INTO videojuegos (sku, titulo, plataforma, formato) VALUES
('VG-FIS-001', 'The Legend of Zelda: BOTW',  'Nintendo Switch', 'Físico'),
('VG-FIS-002', 'EA Sports FC 24',            'PS5',             'Físico'),
('VG-FIS-003', 'Elden Ring',                 'Xbox Series X',   'Físico'),
('VG-CRD-001', 'Tarjeta Saldo $10.000',      'PlayStation',     'Físico'); 

INSERT INTO inventario_bodegas (bodega_id, videojuego_id, stock_actual, stock_minimo) VALUES
-- Stock consolidado en la Bodega Central (Mayores volúmenes)
(1, 1, 150, 20),
(1, 2, 300, 50),
(1, 3, 80,  15),
(1, 4, 500, 100),

-- Stock en la Tienda Centro (Alerta de reabastecimiento para Zelda, stock_actual < stock_minimo)
(2, 1, 3,   10),
(2, 2, 45,  15),
(2, 4, 120, 30),

-- Stock en Tienda Mall (Quiebre de stock de Elden Ring)
(3, 1, 25,  10),
(3, 3, 0,   5);