-- имена всех person, которые не состоят в компании с id = 5;

select 
	p.name
from person p
join company c on c.id = p.company_id
where c.id != 5;

-- название компании для каждого человека

select
	p.name as person_name,
	c.name as company_name
from person p
join company c on c.id = p.company_id;

-- Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании

with base as ( 
		select 
			c.name as company_name,
			count(p.id) as person_amount
		from person p
		join company c on c.id = p.company_id
		group by 1
)
select *
from base b
where b.person_amount = (select max(person_amount) from base);