\c usuarios;

-- 1. ELIMINACIÓN EN JERARQUÍA INVERSA
DROP TABLE IF EXISTS credenciales_usuarios;
DROP TABLE IF EXISTS perfil_usuarios;
DROP TABLE IF EXISTS usuarios;

--Cada tabla maestra tiene su propia clase JPA en el modelo representándolo

-- 2. TABLAS MAESTRAS
CREATE TABLE usuarios (
    id                SERIAL       PRIMARY KEY,
    nickname          VARCHAR(100) UNIQUE NOT NULL,
    email             VARCHAR(150) UNIQUE NOT NULL,
    password          VARCHAR(255) NOT NULL, 
    rol               VARCHAR(50)  NOT NULL CHECK (rol IN ('Administrador','Vendedor','Cliente')),
    activo            BOOLEAN      DEFAULT TRUE
);

CREATE TABLE perfil_usuarios (
    id                SERIAL       PRIMARY KEY,
    usuario_email     VARCHAR(150) UNIQUE NOT NULL REFERENCES usuarios(email) ON DELETE CASCADE,
    telefono          VARCHAR(30),
    direccion         VARCHAR(180),
    fecha_registro    DATE         NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE credenciales_usuarios (
    id                SERIAL       PRIMARY KEY,
    usuario_email     VARCHAR(150) UNIQUE NOT NULL REFERENCES usuarios(email) ON DELETE CASCADE,
    ultimo_acceso     TIMESTAMP,
    bloqueado         BOOLEAN      NOT NULL DEFAULT FALSE,
    intentos_fallidos INT          NOT NULL DEFAULT 0 CHECK (intentos_fallidos >= 0)
);

CREATE INDEX idx_usuarios_rol ON usuarios(rol);
CREATE INDEX idx_usuarios_activo ON usuarios(activo);
CREATE INDEX idx_perfil_usuario_email ON perfil_usuarios(usuario_email);

-- 3. DATOS DE PRUEBA
INSERT INTO usuarios (nickname, email, password, rol) VALUES
--Todas las contraseñas son Biblio@2026
('Ana',      'ana@administrador.cl',     '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Administrador'),
('Andrés',   'andres@administrador.cl',  '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Administrador'),
('Adrián',   'adrian@administrador.cl',  '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Administrador'),
('Beatriz',  'beatriz@vendedor.cl', '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Vendedor'),
('Benito',   'benito@vendedor.cl',  '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Vendedor'),
('Belén',    'belen@vendedor.cl',   '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO',  'Vendedor'),
('Carlos',   'carlos@cliente.cl',        '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 'Cliente'),
('Camila',   'camila@cliente.cl',        '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 'Cliente'),
('Cristian', 'cristian@cliente.cl',      '$2b$10$1hnbaMR7iTsdn3D0gG5Q8eUw5aSh9O2at2e4u1iAlzdhD6m4dzVZO', 'Cliente');


INSERT INTO perfil_usuarios (usuario_email, telefono, direccion) VALUES
('ana@administrador.cl',     '+56911111111', 'Sede Central'),
('beatriz@vendedor.cl', '+56922222222', 'Sede Providencia'),
('carlos@cliente.cl',        '+56933333333', 'Santiago'),
('camila@cliente.cl',        NULL,           'Valparaíso'),
('cristian@cliente.cl',      '+56955555555', 'La Serena');

INSERT INTO credenciales_usuarios (usuario_email, ultimo_acceso, bloqueado, intentos_fallidos) VALUES
('ana@administrador.cl',     NOW(), FALSE, 0),
('beatriz@vendedor.cl', NOW(), FALSE, 0),
('carlos@cliente.cl',        NOW(), FALSE, 1),
('camila@cliente.cl',        NULL,  FALSE, 0),
('cristian@cliente.cl',      NULL,  TRUE,  3);
