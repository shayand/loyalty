-- liquibase formatted sql

-- changeset shayan-davarzani:1749832202856-1
CREATE SEQUENCE IF NOT EXISTS affiliate_seq START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749832202856-4
CREATE SEQUENCE IF NOT EXISTS instance_tiers_seq START WITH 1 INCREMENT BY 1;

-- changeset shayan-davarzani:1749832202856-5
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

-- changeset shayan-davarzani:1749832202856-6

CREATE TABLE affiliate_users
(
    id           BIGINT NOT NULL,
    affiliate_id BIGINT,
    tier_id     BIGINT,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    deleted_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_affiliate_users PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749832202856-7
CREATE TABLE IF NOT EXISTS tiers
(
    id               BIGINT NOT NULL,
    tier_slug        VARCHAR(255),
    title            VARCHAR(100),
    level            INTEGER,
    points_threshold INTEGER,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    deleted_at       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tiers PRIMARY KEY (id)
);

-- changeset shayan-davarzani:1749832202856-8
CREATE TABLE IF NOT EXISTS instances
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

-- changeset shayan-davarzani:1749832202856-11
ALTER TABLE tiers
    ADD CONSTRAINT uc_2186e9459ff6b753b798cbab9 UNIQUE (tier_slug, level, deleted_at);

-- changeset shayan-davarzani:1749832202856-14
ALTER TABLE affiliates
    ADD CONSTRAINT FK_AFFILIATES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES affiliates (id);

-- changeset shayan-davarzani:1749832202856-15
ALTER TABLE affiliate_users
    ADD CONSTRAINT FK_AFFILIATE_USERS_ON_AFFILIATE FOREIGN KEY (affiliate_id) REFERENCES affiliates (id);

-- changeset shayan-davarzani:1749832202856-16
ALTER TABLE affiliate_users
    ADD CONSTRAINT FK_AFFILIATE_USERS_ON_TIERS FOREIGN KEY (tier_id) REFERENCES tiers (id);
