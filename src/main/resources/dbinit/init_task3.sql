DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS services CASCADE;
DROP TABLE IF EXISTS masters CASCADE;
DROP TABLE IF EXISTS receipts CASCADE;
DROP TABLE IF EXISTS reception_log CASCADE;

CREATE TABLE clients (
  ID       SERIAL PRIMARY KEY,
  fName    VARCHAR(20) NOT NULL,
  lName    VARCHAR(20),
  phone    VARCHAR(20),
  email    VARCHAR(50),
  birthday DATE
);

CREATE TABLE services (
  ID       SERIAL PRIMARY KEY,
  name     VARCHAR(50) NOT NULL,
  price    DECIMAL     not null,
  duration INT         not null
);

CREATE TABLE masters (
  ID        serial primary key,
  serviceID int         not null REFERENCES services (ID),
  fName     varchar(20) not null,
  lName     varchar(20) not null,
  phone     varchar(20) not null,
  email     varchar(50) not null,
  UNIQUE (fName, email)
);

create table receipts (
  ID         serial primary key,
  clientId   int     not null references clients (ID),
  totalPrice decimal not null,
  orderDate  date
);

create table reception_log (
  ID        serial primary key,
  clientId  int     not null references clients (ID),
  serviceId int     not null references services (ID),
  masterId  int     not null references masters (ID),
  logDate   date    not null default now(),
  price     decimal not null
);

INSERT INTO clients (ID, fName, lName, phone, email, birthday)
VALUES (1, 'fName1', 'lName1', '+380671111111', 'email1@mail.com', '2000-01-01'),
       (2, 'fName2', 'lName2', '+380672222222', null, null),
       (3, 'fName3', null, '+380673333333', null, '1977-05-06'),
       (4, 'fName4', 'lName4', '+38067333 44 55', 'email4@gmail.com', null),
       (5, 'fName5', 'lName5', '+38067 456 78 98', 'email5@i.ua', '1987-07-30');

INSERT INTO services (ID, name, price, duration)
VALUES (1, 'Make-Up', 10, 60),
       (2, 'Haircut', 20, 30),
       (3, 'Massage', 30, 50);

insert into masters (ID, serviceID, fName, lName, phone, email)
VALUES (1, 1, 'fNameMaster1', 'lNameMaster2', '0971234566', 'master1@main=l.ua'),
       (2, 1, 'fNameMaster2', 'lNameMaster2', '0971234567', 'master2@main=l.ua'),
       (3, 2, 'fNameMaster3', 'lNameMaster3', '0971234568', 'master3@main=l.ua'),
       (4, 3, 'fNameMaster4', 'lNameMaster4', '0971234569', 'master4@main=l.ua'),
       (5, 3, 'fNameMaster5', 'lNameMaster5', '0971234560', 'master5@main=l.ua');

insert into reception_log (ID, clientId, serviceId, masterId, logDate, price)
VALUES (1, 1, 1, 1, '2017-02-02', 10),
       (2, 1, 2, 3, '2017-02-02', 30),
       (3, 2, 3, 4, '2018-05-02', 50),
       (4, 3, 3, 5, '2016-01-02', 50),
       (5, 3, 2, 3, '2017-08-02', 30),
       (6, 3, 1, 2, '2017-08-02', 60);

insert into receipts (ID, clientId, totalPrice, orderDate)
VALUES (1, 1, 40, '2017-02-02'),
       (2, 2, 50, '2018-05-02'),
       (3, 3, 50, '2016-01-02'),
       (4, 3, 90, '2017-08-02');