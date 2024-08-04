begin;
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
savepoint first_sv;
update products set price = 75 where name = 'product_1';
savepoint second_sv;
drop table products;
rollback to first_sv;
commit;
