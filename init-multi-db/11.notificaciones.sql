-- 1. Conexión
\c notificaciones

-- 2. Eliminación
DROP TABLE IF EXISTS alertas;

-- 3. Creación
CREATE TABLE alertas (
    id_alerta SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    mensaje TEXT NOT NULL,
    leido BOOLEAN DEFAULT FALSE,
    tipo VARCHAR(20) -- 'pago', 'envio', 'oferta'
);

-- 4. Datos de prueba
INSERT INTO alertas (id_usuario, mensaje, tipo) VALUES 
(1, 'Tu pedido ha sido enviado', 'envio'),
(2, 'Tienes un cupón de 10% disponible', 'oferta'),
(3, '', 'pago'); -- Caso borde: Mensaje vacío (error de sistema)

-- 5. Documentación: Historial de comunicaciones enviadas a los usuarios.