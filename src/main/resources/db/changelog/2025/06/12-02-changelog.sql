-- liquibase formatted sql

-- changeset shayan-davarzani:1749742340630-1
CREATE SEQUENCE IF NOT EXISTS affiliate_seq START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749742340630-2
CREATE SEQUENCE IF NOT EXISTS affiliate_user_points START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749742340630-3
CREATE SEQUENCE IF NOT EXISTS instance_action START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749742340630-4
CREATE SEQUENCE IF NOT EXISTS instance_tiers_seq START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749742340630-5
CREATE TABLE affiliate_users
(
    id           BIGINT NOT NULL,
    affiliate_id BIGINT,
    instance_id  UUID,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    deleted_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_affiliate_users PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749742340630-6
CREATE TABLE affiliates
(
    id               BIGINT       NOT NULL,
    affiliation_code VARCHAR(255),
    indicator_type   VARCHAR(255),
    indicator_value  VARCHAR(255) NOT NULL,
    parent_id        BIGINT,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    deleted_at       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_affiliates PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749742340630-7
CREATE TABLE affliate_user_points
(
    id                BIGINT NOT NULL,
    affiliate_user_id BIGINT,
    action_id         BIGINT,
    point             INTEGER,
    created_at        TIMESTAMP WITHOUT TIME ZONE,
    updated_at        TIMESTAMP WITHOUT TIME ZONE,
    deleted_at        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_affliate_user_points PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749742340630-8
CREATE TABLE  IF NOT EXISTS instance_action
(
    id             BIGINT NOT NULL,
    tier_id        BIGINT,
    slug           VARCHAR(100),
    description    VARCHAR(255),
    point_achieved INTEGER,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    deleted_at     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_instance_action PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749742340630-9
CREATE TABLE  IF NOT EXISTS instance_tiers
(
    id               BIGINT NOT NULL,
    instance_id      UUID,
    title            VARCHAR(100),
    level            INTEGER,
    points_threshold INTEGER,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    deleted_at       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_instance_tiers PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749742340630-10
ALTER TABLE affliate_user_points
    ADD CONSTRAINT uc_2435537e7de2772819197b79f UNIQUE (affiliate_user_id, action_id, deleted_at);

-- changeset shayan-davarzani:1749742340630-11
ALTER TABLE instance_action
    ADD CONSTRAINT uc_a6cac0451816c1593f4cb0b76 UNIQUE (tier_id, slug);

-- changeset shayan-davarzani:1749742340630-12
ALTER TABLE instance_tiers
    ADD CONSTRAINT uc_b19030c6f6b9472b6f1ce5739 UNIQUE (instance_id, level, deleted_at);

-- changeset shayan-davarzani:1749742340630-13
ALTER TABLE affiliates
    ADD CONSTRAINT FK_AFFILIATES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES affiliates (id);

-- changeset shayan-davarzani:1749742340630-14
ALTER TABLE affiliate_users
    ADD CONSTRAINT FK_AFFILIATE_USERS_ON_AFFILIATE FOREIGN KEY (affiliate_id) REFERENCES affiliates (id);

-- changeset shayan-davarzani:1749742340630-15
ALTER TABLE affiliate_users
    ADD CONSTRAINT FK_AFFILIATE_USERS_ON_INSTANCE FOREIGN KEY (instance_id) REFERENCES instances (id);

-- changeset shayan-davarzani:1749742340630-16
ALTER TABLE affliate_user_points
    ADD CONSTRAINT FK_AFFLIATE_USER_POINTS_ON_ACTION FOREIGN KEY (action_id) REFERENCES instance_action (id);

-- changeset shayan-davarzani:1749742340630-17
ALTER TABLE affliate_user_points
    ADD CONSTRAINT FK_AFFLIATE_USER_POINTS_ON_AFFILIATE_USER FOREIGN KEY (affiliate_user_id) REFERENCES affiliate_users (id);

-- changeset shayan-davarzani:1749742340630-18
ALTER TABLE instance_action
    ADD CONSTRAINT FK_INSTANCE_ACTION_ON_TIER FOREIGN KEY (tier_id) REFERENCES instance_tiers (id);

-- changeset shayan-davarzani:1749742340630-19
ALTER TABLE instance_tiers
    ADD CONSTRAINT FK_INSTANCE_TIERS_ON_INSTANCE FOREIGN KEY (instance_id) REFERENCES instances (id);

