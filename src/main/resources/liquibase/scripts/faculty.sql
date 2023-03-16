-- liquibase formatted sql

-- changeset dmatveev:2

CREATE TABLE faculty(
    faculty_id    BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    color         VARCHAR(255) NOT NULL);



-- private Long facultyId;
--         private String name;
--         private String color;