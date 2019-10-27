DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id INT AUTO_INCREMENT PRIMARY KEY,
  price INT DEFAULT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) DEFAULT NULL,
  image VARCHAR(500) DEFAULT NULL,
  date TIMESTAMP DEFAULT NULL,
  max_entrant INT DEFAULT NULL
);

INSERT INTO category VALUES(1, 102, 'Liverpool Trip', 'Visit The Beatles Town, The Beatles were an English rock band formed in Liverpool in 1960. With a line-up comprising John Lennon, ' ||
'Paul McCartney, George Harrison and Ringo Starr, they are regarded as the most influential band of all time.',
'https://culturecdn.fra1.cdn.digitaloceanspaces.com//2016/02/ccs_5437-700x467.jpg', '2012-09-17 18:47:52.69', 12);
INSERT INTO category VALUES(2, 102, 'London Trip', 'Big Ben. Westminster Abbey. St. Paul’s Cathedral. Buckingham Palace. The majesty of London attractions encapsulates a 2000-year history ' ||
'that’s rich in power, innovation and culture. See the best of London’s iconic landmarks, and discover new treasures amongst the old with Big Bus Tours.',
'https://cdn.londonandpartners.com/visit/general-london/areas/river/76709-640x360-houses-of-parliament-and-london-eye-on-thames-from-above-640.jpg', '2012-09-17 18:47:52.69', 12);
INSERT INTO category VALUES(3, 102, 'Milano Museums', 'Milan is one of the most in vogue urban communities in Italy. ' ||
'It likewise holds remarkable and aesthetic attractions, including the largest church in Italy, Leonardo Da Vinci’s The Last Supper painting together with the ' ||
'church of Santa Maria delle Grazie and the acclaimed La Scala Opera House to cite just a few notable examples.',
'https://www.hotelfenice.it/wp-content/uploads/2017/08/milano-duomo.jpg', '2012-09-17 18:47:52.69', 12);