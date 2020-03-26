CREATE Table store (
	id BIGSERIAL NOT NULL primary key,
    name varchar(50),
	address varchar(50),
	city varchar(50),
	state varchar(2),
	zip varchar(5),
	note varchar(100)
)