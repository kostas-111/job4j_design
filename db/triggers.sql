create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

--2.1
create or replace function tax() returns trigger
as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$ LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--2.2
create or replace function tax_before_insert() returns trigger
as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return new;
    END;
$$ LANGUAGE 'plpgsql';

create trigger before_ins_tax_trigger
    before insert
    on products
    for each row
    execute procedure tax_before_insert();

--3.
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function history_prices() returns trigger
as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values (new.name, new.price, now());
        return new;
    END;
$$ LANGUAGE 'plpgsql';

create trigger history_prices_trigger
    after insert
    on products
    for each row
    execute procedure history_prices();
