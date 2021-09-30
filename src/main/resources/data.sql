DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user (
    id  BIGINT AUTO_INCREMENT  PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

INSERT INTO app_user (user_name, password) values ('admin', '$2a$12$Mqh79dwwtgh4aCM/n8acXupxhSX4ysAqkLeLLc85xB2k/VslXrYrS');

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