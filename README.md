SQL for creating table:

create table if not exists users (
user_id serial primary key,
firstname varchar(255) not null,
lastname varchar(255) not null,
username varchar(255) not null,
password varchar(255) not null,
email varchar(255) not null,
created_at timestamp default current_timestamp
);
