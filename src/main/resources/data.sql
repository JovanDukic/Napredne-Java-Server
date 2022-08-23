insert into ambulance (ambulance_name) values
('Ambulance_1'),
('Ambulance_2'),
('Ambulance_3'),
('Ambulance_4'),
('Ambulance_5');

insert into country (country_name) values
('SAD'),
('Canada'),
('Mexico'),
('France'),
('England'),
('Italy'),
('Portugal'),
('Russia'),
('Germany');

insert into doctor (first_name, last_name, title) values
('Doctor_First_Name_1', 'Doctor_Last_Name_1', 'Title_1'),
('Doctor_First_Name_2', 'Doctor_Last_Name_2', 'Title_2'),
('Doctor_First_Name_3', 'Doctor_Last_Name_3', 'Title_3'),
('Doctor_First_Name_4', 'Doctor_Last_Name_4', 'Title_4'),
('Doctor_First_Name_5', 'Doctor_Last_Name_5', 'Title_5');

insert into user_role (role_name) values
("USER"),
("ADMIN");

insert into user (first_name, last_name, email, username, password, gender, phone, age, country_id, enabled) values
('Admin', 'Admin', 'admin@gmail.com', 'admin', '$2a$10$WyT0BU0JHsWNvNAu1f9qzufIFzk3sepl4ld4AOevoO4uP4xNzfSGC', 'male', '+381655204158', '23', 1, 1);

insert into user_roles(user_id, role_id) values
(1, 1),
(1, 2);