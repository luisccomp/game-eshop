create table users(
    id          bigserial      not null,
    first_name  varchar(50)    not null,
    last_name   varchar(50)    not null,
    email       varchar(255)   not null,
    password    varchar(255)   not null,
    description varchar(255)   null,
    active      boolean        not null default true,
    created_at  timestamptz(6) not null default current_timestamp,
    updated_at  timestamptz(6) default current_timestamp,
    constraint pk_user primary key (id)
);

create table roles(
    id   bigserial    not null,
    role varchar(255) not null,
    constraint pk_role primary key (id)
);

create table users_roles(
    id      bigserial not null,
    user_id bigint    not null,
    role_id bigint    not null,
    constraint pk_user_role primary key (id),
    constraint fk_user_role_user foreign key (user_id) references users(id),
    constraint fk_user_role_role foreign key (role_id) references roles(id)
);

insert into roles values (1, 'ADMIN');
insert into roles values (2, 'USER');
insert into roles values (3, 'SELLER');