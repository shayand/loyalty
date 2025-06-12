-- liquibase formatted sql

-- changeset shayan-davarzani:1749235354124-1
INSERT INTO instances (id, name, title, description, created_at,updated_at)
VALUES (uuid_generate_v4(), 'GALLERYGARDI', 'گالری گردی', null, now(),now()),
       (uuid_generate_v4(), 'STATION', 'استیشن', null, now(),now()),
       (uuid_generate_v4(), 'SAFIR', 'سفیر', null, now(),now()),
       (uuid_generate_v4(), 'FORECAST', 'فورکست', null, now(),now());
