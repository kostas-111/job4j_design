create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values 
('Goldfish', 20000, null),
('Wolf', 300, '1200-01-05'),
('Fox', 100, '1830-05-23'),
('Gellyfish', 3000, '1960-01-01');


select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where extract(year from discovery_date)::text < '1950';