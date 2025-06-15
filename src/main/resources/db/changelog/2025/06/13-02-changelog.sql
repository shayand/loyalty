-- liquibase formatted sql

-- changeset shayan-davarzani:1749832202857-1
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp"

-- changeset shayan-davarzani:1749832202857-2
CREATE SEQUENCE IF NOT EXISTS instance_action_seq START WITH 1 INCREMENT BY 1;


-- changeset shayan-davarzani:1749832202857-3
CREATE TABLE instance_action
(
    id             BIGINT NOT NULL,
    instance_id    UUID,
    tier_id        BIGINT,
    slug           VARCHAR(100),
    description    VARCHAR(255),
    point_achieved INTEGER,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    deleted_at     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_instance_action PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749832202857-4
ALTER TABLE instance_action
    ADD CONSTRAINT FK_INSTANCE_ACTION_ON_INSTANCE FOREIGN KEY (instance_id) REFERENCES instances (id);

-- changeset shayan-davarzani:1749832202857-5
ALTER TABLE instance_action
    ADD CONSTRAINT FK_INSTANCE_ACTION_ON_TIERS FOREIGN KEY (tier_id) REFERENCES tiers (id);

-- changeset shayan-davarzani:1749832202857-6
ALTER TABLE instance_action
    ADD CONSTRAINT uc_a6cac0451816c1593f4cb0b76 UNIQUE (instance_id, tier_id, slug);

-- changeset shayan-davarzani:1749832202857-7
CREATE SEQUENCE affiliate_user_points_seq START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749832202857-8
CREATE TABLE affliate_user_points
(
    id                BIGINT NOT NULL,
    affiliate_user_id BIGINT NOT NULL,
    action_id         BIGINT,
    point             INTEGER,
    created_at        TIMESTAMP WITHOUT TIME ZONE,
    updated_at        TIMESTAMP WITHOUT TIME ZONE,
    deleted_at        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_affliate_user_points PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749832202857-9
ALTER TABLE affliate_user_points
    ADD CONSTRAINT uc_2435537e7de2772819197b79f UNIQUE (affiliate_user_id, action_id, deleted_at);


-- changeset shayan-davarzani:1749832202857-10
ALTER TABLE affliate_user_points
    ADD CONSTRAINT FK_AFFLIATE_USER_POINTS_ON_ACTION FOREIGN KEY (action_id) REFERENCES instance_action (id);

-- changeset shayan-davarzani:1749832202857-11
ALTER TABLE affliate_user_points
    ADD CONSTRAINT FK_AFFLIATE_USER_POINTS_ON_AFFILIATE_USER FOREIGN KEY (affiliate_user_id) REFERENCES affiliate_users (id);

-- changeset shayan-davarzani:1749832202857-12
INSERT INTO instances (id, name, title, description, created_at, updated_at)
VALUES (uuid_generate_v4(), 'GALLERYGARDI', 'گالری گردی', null, now(), now()),
       (uuid_generate_v4(), 'STATION', 'استیشن', null, now(), now()),
       (uuid_generate_v4(), 'SAFIR', 'سفیر', null, now(), now()),
       (uuid_generate_v4(), 'FORECAST', 'فورکست', null, now(), now());
