-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS java_challenge;

-- Garantizar privilegios al usuario
GRANT ALL PRIVILEGES ON java_challenge.* TO 'user'@'%';
FLUSH PRIVILEGES;
