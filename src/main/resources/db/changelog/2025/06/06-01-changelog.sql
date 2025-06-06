-- liquibase formatted sql

-- changeset shayan-davarzani:1749235353124-1
CREATE TABLE instances
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    title       VARCHAR(100),
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_instances PRIMARY KEY (id)
);

