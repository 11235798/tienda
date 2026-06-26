\c pedidos;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS lineas_pedido;
DROP TABLE IF EXISTS videojuegos_inventario;
DROP TABLE IF EXISTS pedidos_despacho;

-- 2. TABLAS MAESTRAS
CREATE TABLE pedidos_despacho (
    id                  SERIAL       PRIMARY KEY,
    numero_orden        VARCHAR(20)  UNIQUE NOT NULL,
    direccion_destino   VARCHAR(255) NOT NULL,
    comuna_destino      VARCHAR(100) NOT NULL,
    estado_logistico    VARCHAR(50)  NOT NULL CHECK (estado_logistico IN ('Por Preparar', 'Picking', 'Empacado', 'En Ruta', 'Entregado')),
    responsable_almacen VARCHAR(100) NOT NULL -- Personal logístico a cargo de la orden
);

CREATE TABLE videojuegos_inventario (
    id                  SERIAL       PRIMARY KEY,
    sku                 VARCHAR(20)  UNIQUE NOT NULL,
    titulo              VARCHAR(255) NOT NULL,
    stock_fisico        INT          NOT NULL CHECK (stock_fisico >= 0),
    pasillo_bodega      VARCHAR(50)  NOT NULL -- Ubicación física para facilitar la búsqueda
);

-- 3. TABLA INTERMEDIA (DETALLE DE PICKING / ARMADO DE ORDEN)
CREATE TABLE lineas_pedido (
    id                  SERIAL       PRIMARY KEY,
    pedido_id           INT          NOT NULL REFERENCES pedidos_despacho(id) ON DELETE CASCADE,
    videojuego_id       INT          NOT NULL REFERENCES videojuegos_inventario(id) ON DELETE RESTRICT,
    cantidad            INT          NOT NULL CHECK (cantidad > 0),
    estado_item         VARCHAR(50)  DEFAULT 'Pendiente' CHECK (estado_item IN ('Pendiente', 'Recolectado', 'Sin Stock')),
    UNIQUE (pedido_id, videojuego_id) -- Regla: Un mismo juego no se anota dos veces en la misma orden, solo se suma a "cantidad"
);

-- 4. ÍNDICES DE RENDIMIENTO (Claves para operaciones rápidas en almacén)
CREATE INDEX idx_pedidos_estado ON pedidos_despacho(estado_logistico);
CREATE INDEX idx_inventario_sku ON videojuegos_inventario(sku);
CREATE INDEX idx_lineas_pedido ON lineas_pedido(pedido_id);

-- 5. DATOS DE PRUEBA
INSERT INTO pedidos_despacho (numero_orden, direccion_destino, comuna_destino, estado_logistico, responsable_almacen) VALUES
('ORD-5501', 'San Pablo 1234', 'Lo Prado',         'En Ruta',      'Juan Pérez (Operario de Bodega)'),
('ORD-5502', 'Alameda 4321',   'Estación Central', 'Picking',      'Carlos Ruiz (Supervisor de Almacén)'),
('ORD-5503', 'Las Lilas 789',  'Lo Prado',         'Entregado',    'María López (Operario de Bodega)');

INSERT INTO videojuegos_inventario (sku, titulo, stock_fisico, pasillo_bodega) VALUES
('VG-FIS-001', 'The Legend of Zelda: BOTW',  15, 'Pasillo A - Estante 2'),
('VG-FIS-002', 'EA Sports FC 24',            42, 'Pasillo B - Estante 1'),
('VG-FIS-003', 'Elden Ring (Ed. Coleccionista)', 3, 'Bodega de Seguridad');

INSERT INTO lineas_pedido (pedido_id, videojuego_id, cantidad, estado_item) VALUES
-- Orden 5501: Ya va en ruta, sus ítems fueron recolectados
(1, 1, 1, 'Recolectado'),
(1, 2, 1, 'Recolectado'),

-- Orden 5502: Está en proceso de picking en el almacén
(2, 2, 2, 'Recolectado'),
(2, 3, 1, 'Pendiente'),

-- Orden 5503: Pedido histórico ya entregado
(3, 1, 1, 'Recolectado');