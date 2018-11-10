DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS contracts CASCADE;
DROP TABLE IF EXISTS revenue_recognitions CASCADE;

CREATE TABLE products (
  ID   INT PRIMARY KEY,
  name VARCHAR,
  type VARCHAR
);

CREATE TABLE contracts (
  ID          INT PRIMARY KEY,
  product     INT,
  revenue     DECIMAL,
  date_signed DATE
);

CREATE TABLE revenue_recognitions (
  contract      INT,
  amount        DECIMAL,
  recognized_on DATE,
  days          INT,

  PRIMARY KEY (contract, recognized_on)
);

INSERT INTO contracts (ID, product, revenue, date_signed) VALUES (1,1,15000, now());
INSERT INTO contracts (ID, product, revenue, date_signed) VALUES (2,2,25000, now());
INSERT INTO contracts (ID, product, revenue, date_signed) VALUES (3,3,30000, now());

INSERT INTO products (ID, name, type) VALUES (1, 'word processor', 'W');
INSERT INTO products (ID, name, type) VALUES (2, 'database', 'D');
INSERT INTO products (ID, name, type) VALUES (3, 'spreadsheet', 'S');