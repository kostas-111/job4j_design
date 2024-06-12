insert into roles(role_name)
values 
('admin'),
('manager'),
('techsup');

insert into rules(description)
values
('read'),
('write'),
('change'),
('delete'),
('create');

insert into roles_rules(role_id, rule_id)
values
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,1),
(2,2),
(2,5),
(3,1),
(3,2),
(3,3);

insert into users (second_name, first_name, middle_name, birth_date, mail, role_id)
values
('Яковлев', 'Павел', 'Никифорович', '1975-01-03', 'nikif@mail.com', 1),
('Продавцов', 'Сергей', 'Венедиктович', '1995-02-05', 'ven@mail.com', 2),
('Суппортов', 'Иван', 'Львович', '1980-05-03', 'lion@mail.com', 3);

insert into states(state_name)
values
('Создана'),
('В работе'),
('Выполнение приостановлено'),
('Выполнено');

insert into categories(category_name)
values
('Инцидент'),
('Запрос данных'),
('Отчет');

insert into items(user_id, category_id, state_id)
values
(2, 2, 2),
(3, 1, 4),
(2, 2, 3)

insert into comments(comment_text, item_id)
values
('Подготовка статистики по отгрузке груш в Армавир', 1),
('Статистика нужна директору к 01.07.2024', 1),
('Приложить файл с отчетом об устранении проблемы', 2);

insert into attachs(file_name, file_size, item_id)
values
('report.pdf', 234657, 2);

