\c db_resenas

DROP TABLE IF EXISTS votos_resena;
DROP TABLE IF EXISTS resenas;
DROP TABLE IF EXISTS juegos_proyeccion;

CREATE TABLE juegos_proyeccion (
    sku VARCHAR(50) PRIMARY KEY,
    titulo VARCHAR(150)
);

CREATE TABLE resenas (
    id SERIAL PRIMARY KEY,
    juego_sku VARCHAR(50) REFERENCES juegos_proyeccion(sku),
    user_email VARCHAR(100),
    puntos INTEGER CHECK (puntos BETWEEN 1 AND 5),
    comentario TEXT
);

CREATE TABLE votos_resena (
    id SERIAL PRIMARY KEY,
    resena_id INTEGER REFERENCES resenas(id),
    voto_util BOOLEAN -- TRUE = Útil, FALSE = No útil
);

INSERT INTO juegos_proyeccion VALUES ('ELDER-001', 'Elden Ring');
INSERT INTO resenas (juego_sku, user_email, puntos, comentario) VALUES ('ELDER-001', 'gamer99@gmail.com', 5, 'Obra maestra');