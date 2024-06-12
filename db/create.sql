create table roles (
	id serial primary key,
	role_name text
);

create table rules (
	id serial primary key,
	description text
);

create table roles_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table users (
	id serial primary key,
	second_name varchar(250),
	first_name varchar(250),
    middle_name varchar(250),
	birth_date date,
	mail varchar(250),
	role_id int references roles(id)
);

create table states (
	id serial primary key,
	state_name text
);

create table categories (
	id serial primary key,
	category_name text	
);

create table items (
	id serial primary key,
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table comments (
	id serial primary key,
	comment_text text,
	item_id int references items(id)
);

create table attachs (
	id serial primary key,
	file_name text,
	file_size int,
	item_id int references items(id)
);


