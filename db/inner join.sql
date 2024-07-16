create table product_type_nsi(
	id serial primary key,
	name varchar(128)
);

create table suppliers(
	id serial primary key,
	name varchar(128),
	address varchar(128),
	product_type_id int references product_type_nsi(id)
);

create table products(
	id serial primary key,
    name varchar(128),
    product_type_id int references product_type_nsi(id),
	supplier_id int references suppliers(id)
);

insert into product_type_nsi (name) 
values
('Овощи'),
('Бытовая химия'),
('Фрукты'),
('Канцелярия'),
('Кондитерские изделия');

insert into suppliers(name, address, product_type_id)
values
('ООО Мир сладостей', 'г.Москва, ул. Первая, д. 3', 5),
('АО Тимоша', 'г. Азов, ул. Промышленная, д. 5', 5),
('ООО Экофреш', 'г. Краснодар, ул. Магнитная, д. 78', 1),
('ООО Экофреш', 'г. Краснодар, ул. Магнитная, д. 78', 3),
('АО Химик', 'г.Москва, ул. Филевская, д. 6', 2),
('ООО Мир ручек', 'г.Москва, ул. Вторая, д. 68', 4);

insert into products (name,  product_type_id, supplier_id)
values
('Бананы Эквадор', 3, 4),
('Ручка шариковая', 4, 6),
('Карандаши цветные', 4, 6),
('Мыло хозяйственное', 2, 5),
('Халва', 5, 2);

select 
	s.name as supplier_name,
	p.name as product_name,
	ptn.name as product_type
from suppliers s
inner join products p on p.supplier_id = s.id
inner join product_type_nsi ptn on p.product_type_id = ptn.id

select
		s.*
from suppliers s
inner join product_type_nsi ptn on s.product_type_id = ptn.id
where ptn.name = 'Кондитерские изделия'; 

select
	s.name as supplier_name,
	s.address as supplier_address,
	p.name as product_name
from products p
inner join suppliers s on p.supplier_id = s.id
where p.product_type_id = 4;







