create table type
(
    id serial primary key,
    name varchar(255)
);

create table product
(
    id serial primary key,
    name varchar(255),
	type_id int references type (id),
    expired_date date,
	price float
);

insert into type(name)
values
('СЫР'),
('МОЛОКО'),
('МОРОЖЕНОЕ'),
('ЧАЙ'),
('КОЛБАСА');

insert into product(name, type_id, expired_date, price)
values
('мороженое пломбир', 3, '2024-09-30', 95.5),
('мороженое эскимо', 3, '2024-09-30', 82.4),
('молоко домашнее', 2, '2024-05-30', 50),
('молоко ультрапастеризованное', 2, '2024-12-30', 92.8),
('сыр моцарелла', 1, '2024-05-30', 800),
('сыр дорблю', 1, '2024-09-30', 800),
('колбаса сырокопченая', 5, '2024-09-30', 700),
('колбаса докторская', 5, '2023-09-30', 150),
('чай черный', 4, '2024-12-30', 220),
('чай зеленый', 4, '2024-09-30', 220),
('чай травяной', 4, '2024-09-30', 150);

-- 1.
select 
	pr.name
from product pr
inner join type tp on tp.id = pr.type_id
where tp.name = 'СЫР';

--2.
select 
	name 
from product
where name like '%мороженое%';

--3
select 
	name
from product
where expired_date < now()::date;

--4
select 
	name 
from product
where price = (select max(price) from product);

--5
select 
	tp.name,
	count(*) as quantity
from product pr
inner join type tp on tp.id = pr.type_id
group by 1;

--6
select 
	pr.name
from product pr
inner join type tp on tp.id = pr.type_id
where tp.name in ('СЫР', 'МОЛОКО');

--7
select 
	pr.name,
	tp.name
from product pr
inner join type tp on tp.id = pr.type_id;







