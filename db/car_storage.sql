create table car_bodies
(
	id serial primary key,
	name varchar(256)
);

create table car_engines
(
	id serial primary key,
	name varchar(256)
);

create table car_transmissions
(
	id serial primary key,
	name varchar(256)
);

create table cars
(
	id serial primary key,
	name varchar(256),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values
('Седан'),
('Хэтчебэк'),
('Универсал'),
('Кроссовер'),
('Пикап');

insert into car_engines(name)
values
('Бензиновый'),
('Дизельный'),
('Электрический'),
('Гибридный');

insert into car_transmissions(name)
values 
('Механическая'),
('Автоматическая'),
('Роботизированная');

truncate table cars;

insert into cars(name, body_id, engine_id, transmission_id)
values
('Лада Гранта', 1, 1, 1),
('Лада Веста Кросс', 3, 1, 1),
('УАЗ Патриот', 4, 2, 1),
('Москвич 3', 1, 1, 2),
('Лада Икс Рэй', 4, 1, 1),
('Лэнд Ровер Дефендер', 4, 2, 2),
('Додж РЭМ', 4, 1, 2),
('Тесла', 1, 3, 2),
('Нива Шевроле', null, 1, 1),
('Ниссан Кашкай', 4, 2, null);

--1.
select
	c.id,
	c.name as car_name,
	cb.name as body_name,
	ce.name as engine_name,
	ct.name as transmission_name
from cars c
left join car_bodies cb on cb.id = c.body_id
left join car_engines ce on ce.id = c.engine_id
left join car_transmissions ct on ct.id = c.transmission_id

--2.
select 
	cb.name as body_name
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.name is null;

--3.
select 
	ce.name as engine_name
from cars c 
right join car_engines ce on ce.id = c.engine_id
where c.name is null;

--4.
select 
	ct.name as transmission_name
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.name is null;

