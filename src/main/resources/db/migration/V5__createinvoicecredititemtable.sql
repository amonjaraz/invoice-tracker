CREATE TABLE credit_item (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	description VARCHAR(50),
	quantity NUMERIC,
	price NUMERIC,
	dump boolean,
	invoice_id BIGINT REFERENCES invoice(id)
)