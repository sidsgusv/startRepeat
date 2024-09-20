
create table beers
(
    beer_id int auto_increment primary key,
    name    varchar(50)    not null,
    abv     decimal(10, 1) not null
);

create table users
(
    user_id  int auto_increment primary key,
    username varchar(50) not null,
    is_admin boolean     not null
);