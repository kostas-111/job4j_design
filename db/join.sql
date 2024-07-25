--1.
create table departments
(
    id serial primary key,
    name varchar(255)
);

create table employees
(
    id serial primary key,
    name varchar(255),
	dep_id int references departments(id)
);

insert into departments(name)
values
('Отдел кадров'),
('Отдел закупок'),
('IT-отдел'),
('Бухгалтерия');

insert into employees(name, dep_id)
values
('Байден', 2),
('Путин', 4),
('Медведев', 4),
('Харламов', 2),
('Пупкин', 2),
('Захарченко', 1),
('Маск', 1),
('Бергер', 1);

--2.
select 
	d.name as department_name,
	e.name as person_name
from departments d
left join employees e on d.id = e.dep_id;

select 
	d.name as department_name,
	e.name as person_name
from departments d
right join employees e on d.id = e.dep_id;

select 
	d.name as department_name,
	e.name as person_name
from departments d
full join employees e on d.id = e.dep_id;

select 
	d.name as department_name,
	e.name as person_name
from departments d
cross join employees e;

--3.
select 
	d.name as department_name
from departments d
left join employees e on d.id = e.dep_id
where e.name is null;

--4.
select 
	d.name as department_name,
	e.name as person_name
from employees e 
left join departments d on d.id = e.dep_id;

select 
	d.name as department_name,
	e.name as person_name
from departments d
right join employees e on d.id = e.dep_id;

--5.
create table teens
(
	name varchar(255),
	gender char(1)
);

insert into teens (name, gender)
values
('Вася', 'М'),
('Маша', 'Ж'),
('Николай', 'М'),
('Федор', 'М'),
('Лида', 'Ж');

select 
	t1.name as boy,
	t2.name as girl	
from teens t1
cross join teens t2
where t1.gender <> t2.gender
	and t1.gender = 'М'

