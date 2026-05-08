\c db_notificaciones

DROP TABLE IF EXISTS historial_notificaciones;
DROP TABLE IF EXISTS plantillas;
DROP TABLE IF EXISTS usuarios_proyeccion;

CREATE TABLE usuarios_proyeccion (
    email VARCHAR(100) PRIMARY KEY,
    username VARCHAR(50)
);

CREATE TABLE plantillas (
    evento VARCHAR(50) PRIMARY KEY, -- 'PEDIDO_CONFIRMADO', 'REGISTRO'
    mensaje_template TEXT NOT NULL
);

CREATE TABLE historial_notificaciones (
    id SERIAL PRIMARY KEY,
    user_email VARCHAR(100) REFERENCES usuarios_proyeccion(email),
    tipo VARCHAR(10), -- 'EMAIL', 'PUSH'
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios_proyeccion VALUES ('gamer99@gmail.com', 'dark_knight');
INSERT INTO plantillas VALUES ('WELCOME', 'Hola {{username}}, bienvenido a la tienda.');