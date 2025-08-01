CREATE TABLE table_user (
	column_id BIGSERIAL PRIMARY KEY,
	column_email VARCHAR(64) NOT NULL,
	column_password_hash TEXT NOT NULL
);