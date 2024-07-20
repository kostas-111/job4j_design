create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price)
values
('IPhone X', 120000),
('TV Samsung', 200000),
('Sport Watch Polar', 25000),
('Mouse Xiomi', 1500),
('Laser Printer Canon', 35400),
('AirPods', 43000);

insert into people (name)
values
('Baiden'),
('Putin'),
('Trump'),
('Zelenskiy');

insert into devices_people(device_id, people_id)
values
(1, 1),
(6, 1),
(2, 2),
(3, 2),
(1, 2),
(1, 3),
(2, 3),
(5, 3),
(6, 3),
(4, 4),
(5, 4);

-- 3. Используя агрегатные функции вывести среднюю цену устройств.
select 
	avg(price) 
from devices;

--4. Используя группировку вывести для каждого человека среднюю цену его устройств.
select 
	pl.name, 
	avg(d.price) as average_price
from devices d
inner join devices_people dp on d.id = dp.device_id
inner join people pl on pl.id = dp.people_id
group by 1;

--5. Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
select 
	pl.name, 
	avg(d.price) as average_price
from devices d
inner join devices_people dp on d.id = dp.device_id
inner join people pl on pl.id = dp.people_id
group by 1
having avg(d.price) > 5000;
