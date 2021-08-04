alter table "user" add column status_login boolean default false;

create table sec_roles(
    id bigserial primary key,
    name varchar
);

create table sec_user_roles(
    id bigserial primary key,
    user_id int8,
    role_id int8,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES "user"(id),
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES sec_roles(id)
);