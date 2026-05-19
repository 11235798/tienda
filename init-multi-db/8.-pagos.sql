\c db_pagos;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS transacciones_pago;
DROP TABLE IF EXISTS catalogo_productos;
DROP TABLE IF EXISTS usuarios;

-- 2. TABLAS MAESTRAS
CREATE TABLE usuarios (
    id                SERIAL       PRIMARY KEY,
    nickname          VARCHAR(100) UNIQUE NOT NULL,
    email             VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE catalogo_productos (
    id                SERIAL       PRIMARY KEY,
    sku               VARCHAR(20)  UNIQUE NOT NULL,
    nombre_producto   VARCHAR(255) NOT NULL,
    tipo_producto     VARCHAR(50)  NOT NULL, -- Ej: 'Juego Base', 'Moneda Virtual', 'Suscripción'
    precio_clp        INT          NOT NULL CHECK (precio_clp >= 0)
);

-- 3. TABLA INTERMEDIA (TRANSACCIONES FINANCIERAS)
CREATE TABLE transacciones_pago (
    id                SERIAL       PRIMARY KEY,
    usuario_id        INT          NOT NULL REFERENCES usuarios(id) ON DELETE RESTRICT,
    producto_id       INT          NOT NULL REFERENCES catalogo_productos(id) ON DELETE RESTRICT,
    monto_pagado      INT          NOT NULL CHECK (monto_pagado >= 0),
    metodo_pago       VARCHAR(50)  NOT NULL,
    estado            VARCHAR(20)  DEFAULT 'Completado' CHECK (estado IN ('Pendiente', 'Completado', 'Fallido', 'Reembolsado')),
    fecha_transaccion TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
    -- Nota: Aquí no hay UNIQUE(usuario_id, producto_id) porque un usuario puede comprar monedas virtuales varias veces.
);

-- 4. ÍNDICES DE RENDIMIENTO (Críticos para reportes de ventas)
CREATE INDEX idx_productos_sku ON catalogo_productos(sku);
CREATE INDEX idx_transacciones_usuario ON transacciones_pago(usuario_id);
CREATE INDEX idx_transacciones_producto ON transacciones_pago(producto_id);
CREATE INDEX idx_transacciones_fecha ON transacciones_pago(fecha_transaccion);

-- 5. DATOS DE PRUEBA
INSERT INTO usuarios (nickname, email) VALUES
('Alex_Dev', 'alex@email.com'), 
('CasualGamer', 'casual@email.com'), 
('ProSniper', 'sniper@email.com');

INSERT INTO catalogo_productos (sku, nombre_producto, tipo_producto, precio_clp) VALUES
('FF-DIA-100',  '100 Diamantes - Free Fire',      'Moneda Virtual', 900),
('FN-VB-1000',  '1000 PaVos - Fortnite',          'Moneda Virtual', 7500),
('GD-BASE-PC',  'Geometry Dash',                  'Juego Base',     3500),
('HS-COINS',    'Pack Monedas - Hungry Shark',    'Moneda Virtual', 1500),
('SUB-PASS-01', 'Pase de Batalla Premium',        'Suscripción',    8990);

INSERT INTO transacciones_pago (usuario_id, producto_id, monto_pagado, metodo_pago, estado) VALUES
-- Compras recurrentes de moneda virtual
(1, 1, 900,  'Mercado Pago', 'Completado'),
(1, 1, 900,  'Mercado Pago', 'Completado'),
(3, 2, 7500, 'BE Pay',       'Completado'),

-- Compra de juego base
(2, 3, 3500, 'Tarjeta de Crédito', 'Completado'),

-- Compra de paquete para juegos móviles
(1, 4, 1500, 'Mercado Pago', 'Completado'),

-- Transacciones con otros estados
(2, 5, 8990, 'BE Pay', 'Fallido'),
(3, 1, 900,  'Tarjeta de Débito', 'Pendiente');