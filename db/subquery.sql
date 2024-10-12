
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);


CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers
VALUES (1,'Доу', 'Джон', 20, 'Англия'),
       (2,'Грубер', 'Ганс', 33, 'Нидерланды'),
       (3,'Смит', 'Сара', 22, 'ЮАР'),
       (4,'Иванов', 'Иван', 78, 'Белоруссия'),
       (5,'Купер', 'Грета', 35, 'Украина');
      
INSERT INTO orders
VALUES (1, 200, 1),
	   (2, 100, 1),
       (3, 45, 2),
       (4,220, 3),
       (5,120, 3),
       (6,10, 3),
       (7,50, 3);
      
select c.*
from customers c
where c.id not in (select o.customer_id from orders o);
           
      
      
      
      
