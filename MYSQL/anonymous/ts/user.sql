create table user
(
    id       int auto_increment comment 'ID',
    name     varchar(20)   null comment '姓名',
    username varchar(20)   null comment '用户名',
    age      int           null comment '年龄',
    sex      varchar(2)    null comment '性别',
    phone    varchar(15)   null comment '联系方式',
    address  varchar(200)  null comment '地址',
    account  int default 0 null,
    constraint table_name_id_uindex
        unique (id)
);

alter table user
    add primary key (id);

