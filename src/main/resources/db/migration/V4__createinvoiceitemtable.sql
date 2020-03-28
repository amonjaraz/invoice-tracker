CREATE TABLE invoice_item (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	description VARCHAR(50),
	quantity NUMERIC,
	price NUMERIC,
	invoice_id BIGINT REFERENCES invoice(id)
)