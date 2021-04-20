

DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name  VARCHAR(100) NOT NULL,
  created_timestamp TIMESTAMP NOT NULL,
  last_updated_timestamp TIMESTAMP NOT NULL,
  is_deleted BOOLEAN DEFAULT 'FALSE'
);

DROP TABLE IF EXISTS book;

CREATE TABLE book (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  total_page INT NOT NULL,
  published_date TIMESTAMP NULL,
  created_timestamp TIMESTAMP NOT NULL,
  last_updated_timestamp TIMESTAMP NOT NULL,
  author_id INT
);

ALTER TABLE book
ADD CONSTRAINT book_author FOREIGN KEY (author_id) references author(id);