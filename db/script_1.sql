create table pupils (
		id serial primary key,
		first_name varchar(250),
		second_name varchar(250),
		middle_name varchar(250),
		birth_date date
);

insert into pupils (first_name, second_name, middle_name, birth_date)
values('Эдуард', 'Воробьев', 'Васильевич', '1992-02-15');

update pupils 
set birth_date = '1993-02-15'
where id = 1;

delete from pupils;
