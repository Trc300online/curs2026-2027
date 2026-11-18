-- Crea la base de dades de les jornades des de zero. Per carregar-la, o per tornar a començar:
--   docker compose exec mysql sh -c "mysql -udwes -pdwes < /apps/5-jdbc/sql/jornades.sql"

SET NAMES utf8mb4;      -- el client ha d'enviar els accents en UTF-8

DROP DATABASE IF EXISTS jornades;
CREATE DATABASE jornades CHARACTER SET utf8mb4;
USE jornades;

CREATE TABLE usuari (
    nom         VARCHAR(30)  PRIMARY KEY,
    nom_complet VARCHAR(100) NOT NULL,
    contrasenya VARCHAR(100) NOT NULL,      -- només per aprendre! Es guarda el hash: ho farem amb Spring Security
    admin       BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE taller (
    id       VARCHAR(20)  PRIMARY KEY,
    nom      VARCHAR(100) NOT NULL,
    places   INT          NOT NULL,
    inscrits INT          NOT NULL DEFAULT 0
);

CREATE TABLE inscripcio (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    usuari VARCHAR(30) NOT NULL,
    taller VARCHAR(20) NOT NULL,
    FOREIGN KEY (usuari) REFERENCES usuari (nom),
    FOREIGN KEY (taller) REFERENCES taller (id),
    UNIQUE (usuari, taller)                 -- ningú no es pot inscriure dues vegades al mateix taller
);

INSERT INTO usuari (nom, nom_complet, contrasenya, admin) VALUES
    ('anna',  'Anna Ferrà',       'anna123',  FALSE),
    ('marc',  'Marc Riera',       'marc123',  FALSE),
    ('admin', 'L''administradora', 'admin123', TRUE);

INSERT INTO taller (id, nom, places) VALUES
    ('git',    'Git i GitHub sense por',        20),
    ('docker', 'Docker per a principiants',      2),
    ('ia',     'Programar amb assistents d''IA', 30);
