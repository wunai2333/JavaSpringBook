create table category
(
    id     int auto_increment,
    name   varchar(20)  null comment 'ming chen',
    remark varchar(250) null comment 'bei zhu',
    pid    int          null comment 'fu ji ID',
    constraint category_id_uindex
        unique (id)
);

alter table category
    add primary key (id);

