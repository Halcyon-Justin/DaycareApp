-- To avoid foreign-key issues IN ORDER:

-- roles

-- users

-- home_address

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
-- HOME ADDRESSES
-- =========================

INSERT INTO home_address (street_address, city, state, zip_code) VALUES
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

INSERT INTO child (first_name,last_name,date_of_birth,is_active,family_id,notes) VALUES
('Emma','Smith','2016-04-12',TRUE,1,'Enjoys soccer'),
('Liam','Smith','2014-09-03',TRUE,1,'Allergic to peanuts'),

('Olivia','Johnson','2018-01-22',TRUE,2,'Loves drawing'),
('Noah','Johnson','2015-07-15',FALSE,2,'Moved recently'),

('Ava','Williams','2017-11-30',TRUE,3,'Very talkative'),
('Ethan','Williams','2013-06-08',TRUE,3,'Enjoys math puzzles'),

('Sophia','Brown','2019-02-14',TRUE,4,'Loves animals'),
('Mason','Brown','2012-10-21',FALSE,4,'Transferred schools'),

('Isabella','Davis','2016-05-05',TRUE,5,'Plays piano'),
('Lucas','Davis','2014-12-18',TRUE,5,'Very competitive in sports');

-- =========================
-- GUARDIANS
-- =========================

INSERT INTO guardian (first_name,last_name,phone_number,email_address,family_id,relationship,is_emergency_contact) VALUES
('John','Smith','5551001','[john.smith@email.com](mailto:john.smith@email.com)',1,'FATHER',TRUE),
('Sarah','Smith','5551002','[sarah.smith@email.com](mailto:sarah.smith@email.com)',1,'MOTHER',FALSE),

('David','Johnson','5552001','[david.johnson@email.com](mailto:david.johnson@email.com)',2,'FATHER',TRUE),
('Emily','Johnson','5552002','[emily.johnson@email.com](mailto:emily.johnson@email.com)',2,'MOTHER',FALSE),

('Michael','Williams','5553001','[michael.williams@email.com](mailto:michael.williams@email.com)',3,'FATHER',TRUE),
('Laura','Williams','5553002','[laura.williams@email.com](mailto:laura.williams@email.com)',3,'MOTHER',FALSE),

('Robert','Brown','5554001','[robert.brown@email.com](mailto:robert.brown@email.com)',4,'FATHER',TRUE),
('Karen','Brown','5554002','[karen.brown@email.com](mailto:karen.brown@email.com)',4,'MOTHER',FALSE),

('Daniel','Davis','5555001','[daniel.davis@email.com](mailto:daniel.davis@email.com)',5,'FATHER',TRUE),
('Michelle','Davis','5555002','[michelle.davis@email.com](mailto:michelle.davis@email.com)',5,'MOTHER',FALSE);

-- =========================
-- INVOICES
-- =========================

INSERT INTO invoice (family_id,due_date,amount_due,status) VALUES
(1,'2026-01-15',15000,'UNPAID'),
(1,'2026-02-15',12000,'UNPAID'),

(2,'2026-01-10',18000,'UNPAID'),
(2,'2026-03-01',9500,'UNPAID'),

(3,'2026-02-01',21000,'UNPAID'),
(3,'2026-03-01',14000,'UNPAID'),

(4,'2026-01-20',8000,'UNPAID'),
(4,'2026-02-20',8000,'UNPAID'),

(5,'2026-01-25',17500,'UNPAID'),
(5,'2026-02-25',17500,'UNPAID');
