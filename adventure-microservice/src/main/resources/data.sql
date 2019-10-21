DROP TABLE IF EXISTS adventure;

CREATE TABLE adventure (
  id INT AUTO_INCREMENT PRIMARY KEY,
  price INT DEFAULT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) DEFAULT NULL,
  date TIMESTAMP DEFAULT NULL,
  max_entrant INT DEFAULT NULL
);

INSERT INTO adventure VALUES(1, 102, 'Liverpool Trip', 'Discover the beatles', '2012-09-17 18:47:52.69', 12);
INSERT INTO adventure VALUES(2, 102, 'Liverpool Trip', 'Discover the beatles', '2012-09-17 18:47:52.69', 12);
INSERT INTO adventure VALUES(3, 102, 'Liverpool Trip', 'Discover the beatles', '2012-09-17 18:47:52.69', 12);
