-- Crea la base de dades des de zero. Per carregar-la, o per tornar a començar, des de la carpeta codi-explicatiu:
--   docker compose exec mysql sh -c "mysql -udwes -pdwes < /apps/incidencies-jpa/sql/incidencies.sql"

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS aula;
CREATE DATABASE aula CHARACTER SET utf8mb4;
USE aula;

CREATE TABLE incidencia (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    aula       VARCHAR(20)  NOT NULL,
    descripcio VARCHAR(500) NOT NULL,
    prioritat  VARCHAR(10)  NOT NULL,
    resolta    BOOLEAN      NOT NULL DEFAULT FALSE
);

INSERT INTO incidencia (aula, descripcio, prioritat) VALUES
    ('A12', 'El projector no s''encén',            'alta'),
    ('B04', 'Falten dos teclats',                  'baixa'),
    ('A07', 'No hi ha Wi-Fi a la fila del fons',   'mitjana');
