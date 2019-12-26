DROP TABLE IF EXISTS category;

CREATE SEQUENCE category_pkey;

CREATE TABLE category (
  id INT NOT NULL PRIMARY KEY DEFAULT nextval('category_pkey'),
  name VARCHAR(50) NOT NULL,
  color VARCHAR(50) DEFAULT NULL
);

ALTER SEQUENCE category_pkey OWNED BY category.id;