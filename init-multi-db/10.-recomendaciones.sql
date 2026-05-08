\c db_recomendaciones

DROP TABLE IF EXISTS logs_IA;
DROP TABLE IF EXISTS sugerencias;
DROP TABLE IF EXISTS usuarios_proyeccion;

CREATE TABLE usuarios_proyeccion (
    email VARCHAR(100) PRIMARY KEY,
    intereses_tags VARCHAR(255) -- 'rpg, action, openworld'
);

CREATE TABLE sugerencias (
    id SERIAL PRIMARY KEY,
    user_email VARCHAR(100) REFERENCES usuarios_proyeccion(email),
    juego_sku_sugerido VARCHAR(50),
    score_confianza DECIMAL(3,2) -- 0.99 para alta recomendación
);

CREATE TABLE logs_IA (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resultado_modelo VARCHAR(100)
);

INSERT INTO usuarios_proyeccion VALUES ('gamer99@gmail.com', 'rpg, souls-like');
INSERT INTO sugerencias (user_email, juego_sku_sugerido, score_confianza) VALUES ('gamer99@gmail.com', 'ELDER-001', 0.95);