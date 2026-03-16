-- To avoid foreign-key issues IN ORDER:

-- roles

-- users

-- address

-- family

-- child

-- guardian

-- invoice

-- =========================
-- ROLES
-- =========================

INSERT INTO roles (id,name) VALUES
(1,'ADMIN'),
(2,'USER'),
(3,'GUEST');

-- =========================
-- USERS
-- =========================

INSERT INTO users (username,password,role_id,enabled) VALUES
('admin','$2a$10$BQ/hoaOYzT4A.KZP09NZ8.YwtV1UxXjc8I5GbuP2HukitjLj5830y',1,TRUE),
('staff','$2a$10$Dow1F9h1u1qP9PqH6vS9KOLn7Yj5pZr4Qq5KjQj9eJpQe4yM6aY2G',2,TRUE),
('guest','$2a$10$Dow1F9h1u1qP9PqH6vS9KOLn7Yj5pZr4Qq5KjQj9eJpQe4yM6aY2G',3,TRUE);

-- =========================
-- ADDRESSES
-- =========================

INSERT INTO address (street_address, city, state, zip_code) VALUES
('123 Maple St','Springfield','IL','62701'),
('456 Oak Ave','Madison','WI','53703'),
('789 Pine Dr','Minneapolis','MN','55401'),
('321 Cedar Ln','Chicago','IL','60601'),
('654 Birch Blvd','St Paul','MN','55102');

-- =========================
-- FAMILIES
-- =========================

INSERT INTO family (address_id) VALUES
(1),
(2),
(3),
(4),
(5);

-- =========================
-- CHILDREN
-- =========================

INSERT INTO child (first_name,last_name,date_of_birth,family_id,enrollment_date,withdrawal_date,notes) VALUES
('Emma','Smith','2016-04-12',1,'2016-04-12','2016-04-12','Enjoys soccer'),
('Liam','Smith','2014-09-03',1,'2016-04-12','2016-04-12','Allergic to peanuts'),

('Olivia','Johnson','2018-01-22',2,'2016-04-12','2016-04-12','Loves drawing'),
('Noah','Johnson','2015-07-15',2,'2016-04-12','2016-04-12','Moved recently'),

('Ava','Williams','2017-11-30',3,'2016-04-12','2016-04-12','Very talkative'),
('Ethan','Williams','2013-06-08',3,'2016-04-12','2016-04-12','Enjoys math puzzles'),

('Sophia','Brown','2019-02-14',4,'2016-04-12','2016-04-12','Loves animals'),
('Mason','Brown','2012-10-21',4,'2016-04-12','2016-04-12','Transferred schools'),

('Isabella','Davis','2016-05-05',5,'2016-04-12','2016-04-12','Plays piano'),
('Lucas','Davis','2014-12-18',5,'2016-04-12','2016-04-12','Very competitive in sports');

-- =========================
-- GUARDIANS
-- =========================

INSERT INTO guardian (first_name,last_name,phone_number,email_address,family_id) VALUES
('John','Smith','5551001','john.smith@email.com',1),
('Sarah','Smith','5551002','sarah.smith@email.com',1),

('David','Johnson','5552001','david.johnson@email.com',2),
('Emily','Johnson','5552002','emily.johnson@email.com',2),

('Michael','Williams','5553001','michael.williams@email.com',3),
('Laura','Williams','5553002','laura.williams@email.com',3),

('Robert','Brown','5554001','robert.brown@email.com',4),
('Karen','Brown','5554002','karen.brown@email.com',4),

('Daniel','Davis','5555001','daniel.davis@email.com',5),
('Michelle','Davis','5555002','michelle.davis@email.com',5);

-- =========================
-- INVOICES
-- =========================

INSERT INTO invoice (family_id,due_date,pay_date,amount,status) VALUES
(1,'2026-01-15','2026-01-15',15000,'UNPAID'),
(1,'2026-02-15','2026-01-15',12000,'UNPAID'),

(2,'2026-01-10','2026-01-15',18000,'UNPAID'),
(2,'2026-03-01','2026-01-15',9500,'PAID'),

(3,'2026-02-01','2026-01-15',21000,'PARTIAL'),
(3,'2026-03-01','2026-01-15',14000,'UNPAID'),

(4,'2026-01-20','2026-01-15',8000,'UNPAID'),
(4,'2026-02-20','2026-01-15',8000,'UNPAID'),

(5,'2026-01-25','2026-01-15',17500,'PAID'),
(5,'2026-02-25','2026-01-15',17500,'UNPAID');


UPDATE family
set primary_guardian_id = 1
where id = 1;

UPDATE family
set primary_guardian_id = 3
where id = 2;

UPDATE family
set primary_guardian_id = 5
where id = 3;

UPDATE family
set primary_guardian_id = 7
where id = 4;

UPDATE family
set primary_guardian_id = 9
where id = 5;