ALTER TABLE table_paste
	ADD COLUMN column_author BIGINT
;

ALTER TABLE table_paste
	ADD CONSTRAINT fk_paste_author
		FOREIGN KEY (column_author)
		REFERENCES table_user (column_id)
		ON DELETE SET NULL
;