-- liquibase formatted sql

-- changeset dmatveev:3

CREATE TABLE avatar (
     avatar_id  BIGSERIAL PRIMARY KEY,
     file_path  VARCHAR(255),
     file_size  BIGINT,
     media_type VARCHAR(255),
     data       BYTEA,
     student_id BIGINT REFERENCES student (id));

