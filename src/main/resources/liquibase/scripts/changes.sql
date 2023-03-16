-- liquibase formatted sql

-- changeset dmatveev:4
ALTER TABLE faculty
ADD UNIQUE (name);

-- changeset dmatveev:5
ALTER TABLE student
ADD CONSTRAINT check_positive check (age > 11);
