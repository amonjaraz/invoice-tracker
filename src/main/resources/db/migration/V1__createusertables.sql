CREATE TABLE user_table (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	user_name varchar(50),
	email varchar(50),
	password varchar(50),
	role varchar(50)
)
