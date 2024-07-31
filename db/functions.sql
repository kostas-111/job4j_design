create table products_procedure
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products_procedure (name, producer, count, price)
values 
('product_2', 'producer_2', 15, 32),
('product_1', 'producer_1', 3, 50),
('product_3', 'producer_3', 8, 115);

create or replace function delete_func(amount int, max_price int) 
returns void
language 'plpgsql'
as $$
    BEGIN
        delete 
        from products_procedure
        where count < amount and price >= max_price;
    END;
$$;

create or replace procedure delete_rows_proc(producer_name text, max_price int)
language 'plpgsql'
as $$
    BEGIN
        delete 
        from products_procedure
        where name = producer_name and price >= max_price;
    END;
$$;
