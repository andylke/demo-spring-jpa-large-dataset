CREATE TABLE message (
  id UUID NOT NULL,
  code VARCHAR(10) NOT NULL,
  text VARCHAR(50) NOT NULL
);

ALTER TABLE message
  ADD CONSTRAINT message_pk
  PRIMARY KEY (id);

ALTER TABLE message
  ADD CONSTRAINT message_uk_code
  UNIQUE (code);
