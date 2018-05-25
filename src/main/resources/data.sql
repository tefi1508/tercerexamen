delete from comment;
delete from  restaurant;
delete from Category;
delete from city;

delete from User;


INSERT INTO category(id, nombre) VALUES (100, 'Pizza');
INSERT INTO category(id, nombre) VALUES (101, 'Comida Rapida');
INSERT INTO category(id, nombre) VALUES (102, 'Heladeria');
INSERT INTO category(id, nombre) VALUES (103, 'Comidas exóticas');
INSERT INTO category(id, nombre) VALUES (104, 'Pastas');


INSERT INTO city(id, nombre) VALUES (50,'Cochabamba');
INSERT INTO city(id, nombre) VALUES (51,'La Paz');
INSERT INTO city(id, nombre) VALUES (52,'Santa Cuz');
INSERT INTO city(id, nombre) VALUES (53,'Chuquisaca');
INSERT INTO city(id, nombre) VALUES (54,'Oruro');
INSERT INTO city(id, nombre) VALUES (55,'Pando');
INSERT INTO city(id, nombre) VALUES (56,'Tarija');
INSERT INTO city(id, nombre) VALUES (57,'Beni');
INSERT INTO city(id, nombre) VALUES (58,'Potosi');


INSERT INTO restaurant(id, name,phone,description,likes,category_id,city_id) VALUES (50,'Elis',4785698,'Muy bueno',0,100,50);
INSERT INTO restaurant(id, name,phone,description,likes,category_id,city_id) VALUES (51,'Burger king',4569823,'Muy bueno igualmente xd',0,101,50);
INSERT INTO restaurant(id, name,phone,description,likes,category_id,city_id) VALUES (52,'Tropical Chicken',4965832,'Bueno',0,101,50);



INSERT INTO user (id,admin,last_name,name,password,username) VALUES (1,1,'b','a','$2a$10$QVUYllnp1PMD4aQN/TDese/L78dN0ABWhnolJl0xmMtmTt.7ajx72','a');

