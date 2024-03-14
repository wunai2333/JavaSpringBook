create table book
(
    id          int auto_increment,
    name        varchar(20)   not null comment '图书名称',
    description varchar(500)  not null comment '图书描述',
    publish     varchar(30)   not null comment '出版日期',
    author      varchar(60)   not null comment '作者',
    publisher   varchar(60)   not null comment '出版社',
    category    varchar(255)  not null comment '分类',
    book_no     varchar(255)  not null comment '编号',
    cover       varchar(255)  null comment '封面
',
    score       int           not null comment '积分',
    nums        int default 0 null,
    constraint book_book_no_uindex
        unique (book_no),
    constraint book_id_uindex
        unique (id)
);

alter table book
    add primary key (id);

