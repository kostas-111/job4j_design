create table products
(
	id       serial primary key,
	name     varchar(50),
	producer varchar(50),
	count    integer default 0,
	price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);

begin transaction isolation level serializable;
update products set count = 26 where name = 'product_1';
update products set count = 30 where name = 'product_1';
commit;