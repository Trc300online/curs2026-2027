-- La mateixa base de dades de 2b-model-dao i tallers-sessions: JPA s'adapta a les taules que ja hi ha.
-- Si ja la teniu carregada, no cal tornar-ho a fer. Aquest script l'esborra i en deixa només els tallers d'exemple.
--
-- Crea la base de dades dels tallers des de zero. Per carregar-la, o per tornar a començar:
--   docker compose exec mysql sh -c "mysql -udwes -pdwes < /apps/6-jpa/sql/tallers.sql"

SET NAMES utf8mb4;      -- el client ha d'enviar els accents en UTF-8

DROP DATABASE IF EXISTS tallers;
CREATE DATABASE tallers CHARACTER SET utf8mb4;
USE tallers;

-- Una taula = una classe del model. Cada columna serà un camp de model.Taller
CREATE TABLE taller (
    id     VARCHAR(20)  PRIMARY KEY,         -- el codi que surt a la URL: /taller?id=git
    nom    VARCHAR(100) NOT NULL,
    ponent VARCHAR(100) NOT NULL,
    dia    DATE         NOT NULL,            -- en Java, LocalDate
    places INT          NOT NULL,
    preu   DECIMAL(6,2) NOT NULL DEFAULT 0   -- en Java, BigDecimal: els doubles no són exactes amb els cèntims
);

-- Els mateixos tallers que a 2-jsp, que allà eren dins una List.of(...)
INSERT INTO taller (id, nom, ponent, dia, places, preu) VALUES
    ('git',    'Git i GitHub sense por',         'Aina Ferrà',  '2026-11-24', 20,  0),
    ('docker', 'Docker per a principiants',      'Pau Mir',     '2026-11-24', 15, 12.50),
    ('ia',     'Programar amb assistents d''IA', 'Marta Riera', '2026-11-25', 30, 15),
    ('xss',    'Seguretat web: XSS & companyia', 'Joan Serra',  '2026-11-26', 12,  5);
