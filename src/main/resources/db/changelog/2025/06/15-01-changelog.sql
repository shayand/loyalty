-- liquibase formatted sql

-- changeset shayan-davarzani:1750003786498-1
ALTER TABLE affiliate_users
    ADD COLUMN instance_id UUID NOT NULL;

-- changeset shayan-davarzani:1750003786498-2
ALTER TABLE affiliate_users
    ADD CONSTRAINT FK_AFFILIATE_USERS_INSTANCE FOREIGN KEY (instance_id) REFERENCES instances (id);