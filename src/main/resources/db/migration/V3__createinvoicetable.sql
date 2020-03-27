CREATE TABLE invoice (
	id BIGSERIAL not null primary key,
	invoice_number BIGSERIAL,
	date_created timestamp,
	created_by varchar(20) not null,
	terms varchar(10) not null,
	note varchar(50) not null,
	discount numeric,
	commission_pay_date date,
	store_id BIGINT REFERENCES store(id)
)