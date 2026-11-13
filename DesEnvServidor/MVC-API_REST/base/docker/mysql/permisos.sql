-- S'executa una sola vegada: quan el volum de MySQL és nou.
-- L'usuari dwes (el crea compose.yaml) pot crear i esborrar les bases de dades de cada mòdul.
-- Només per desenvolupar: a un servidor real, cada aplicació té un usuari amb els permisos justos.
GRANT ALL PRIVILEGES ON *.* TO 'dwes'@'%';
