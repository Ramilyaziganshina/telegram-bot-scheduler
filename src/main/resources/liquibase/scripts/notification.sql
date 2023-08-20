-- - liquibase formatted sql
-- changeset rziganshina:1

create sequence hibernate_sequence;

-- changeset rziganshina:2

create table public.notification
(
    id        bigint not null
        primary key,
    chatid    bigint not null,
    date_time timestamp,
    task      varchar(255)
);