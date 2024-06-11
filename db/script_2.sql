create table car_identity (
	id serial primary key,
	VIN varchar(17),
	produce_date date,
	engine_type varchar(250)	
);

create table owners (
	id serial primary key,
	second_name varchar(250),
	first_name varchar(250),
    middle_name varchar(250),
	birth_date date
);

create table cars (
	id serial primary key,
	brand varchar(250),
	model varchar(250),
	owner_id int references owners(id),
	car_id int references car_identity(id) unique
);

create table registration_place (
	id serial primary key,
	department_name text,
	address text	
);

create table cars_reg_place (
	id serial primary key,
	car_id int references cars(id),
	reg_place_id int references registration_place(id)
);