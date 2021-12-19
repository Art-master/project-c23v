create table if not exists users
(
    id                  bigserial not null
        constraint users_pkey
            primary key,
    name                varchar   not null,
    "last_name"         varchar   not null,
    "phone_number"      varchar   not null,
    "friends_ids"       bigint[]  not null,
    "avatar_id"         varchar   not null,
    "conversations_ids" bigint[]  not null,
    "lang"              varchar  not null
);

alter table users
    owner to postgres;