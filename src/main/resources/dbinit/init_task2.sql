DROP SCHEMA IF EXISTS task2 cascade;

CREATE SCHEMA task2 AUTHORIZATION postgres;

CREATE TABLE task2.products (
  ID   INT PRIMARY KEY,
  name VARCHAR,
  type VARCHAR
);

CREATE TABLE task2.contracts (
  ID          INT PRIMARY KEY,
  product     INT,
  revenue     DECIMAL,
  date_signed DATE
);

CREATE TABLE task2.revenue_recognitions (
  contract      INT,
  amount        DECIMAL,
  recognized_on DATE,
  days          INT,

  PRIMARY KEY (contract, recognized_on)
);

INSERT INTO task2.contracts (ID, product, revenue, date_signed) VALUES (1,1,15000, now());
INSERT INTO task2.contracts (ID, product, revenue, date_signed) VALUES (2,2,25000, now());
INSERT INTO task2.contracts (ID, product, revenue, date_signed) VALUES (3,3,30000, now());

INSERT INTO task2.products (ID, name, type) VALUES (1, 'word processor', 'W');
INSERT INTO task2.products (ID, name, type) VALUES (2, 'database', 'D');
INSERT INTO task2.products (ID, name, type) VALUES (3, 'spreadsheet', 'S');