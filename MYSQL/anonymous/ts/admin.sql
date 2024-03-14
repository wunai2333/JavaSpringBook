create table admin
(
    id       int auto_increment,
    username varchar(30)                null comment '用户名',
    phone    varchar(30)                null,
    email    varchar(30)                null comment '邮箱',
    password varchar(30) default '1234' null comment '密码',
    constraint admin_id_uindex
        unique (id),
    constraint admin_username_uindex
        unique (username)
);

alter table admin
    add primary key (id);

