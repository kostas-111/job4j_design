create view show_students_with_2_or_more_active_orders
as
select 
	b.name as book_name,
	a.name as book_author,
	s.name as student_name,
	count(*) as orders_amount
from orders o
join students s on s.id = o.student_id
join books b on b.id = o.book_id
join authors a on a.id = b.author_id
where o.active = 't'
group by 1, 2, 3
having count(*) > 1;