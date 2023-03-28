-- liquibase formatted sql

-- changeset dmatveev:2

CREATE TABLE student (
      id         BIGSERIAL PRIMARY KEY,
      age        INTEGER     NOT NULL,
      name       VARCHAR(33) NOT NULL,
      faculty_id BIGINT REFERENCES faculty (faculty_id));
