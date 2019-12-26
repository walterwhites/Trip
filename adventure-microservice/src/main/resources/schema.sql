DROP TABLE IF EXISTS adventure;

CREATE SEQUENCE adventure_pkey;

CREATE TABLE adventure (
  id INT NOT NULL PRIMARY KEY DEFAULT nextval('adventure_pkey'),
  price INT NOT NULL DEFAULT NULL,
  category INT NOT NULL DEFAULT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) DEFAULT NULL,
  image VARCHAR(500) DEFAULT NULL,
  date TIMESTAMP DEFAULT NULL,
  max_entrant INT DEFAULT NULL
);

ALTER SEQUENCE adventure_pkey OWNED BY adventure.id;
