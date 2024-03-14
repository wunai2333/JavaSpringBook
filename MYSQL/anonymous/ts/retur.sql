create table retur
(
    id          int auto_increment,
    book_name   varchar(255)                           null comment '图书名称',
    book_no     int                                    null comment '图书编号',
    user_name   varchar(255)                           null comment '用户姓名',
    user_id     int                                    null comment '用户id',
    user_phone  varchar(255)                           null comment '用户手机号',
    createtime  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updatetime  datetime                               null comment '更新时间',
    score       int                                    null comment '借书积分',
    status      varchar(255) default '已借出'          not null comment '借书状态',
    days        int                                    null comment '借书天数',
    return_date datetime                               null comment '归还日期',
    real_date   datetime                               null comment '已归还日期',
    constraint borrow_id_uindex
        unique (id)
);

alter table retur
    add primary key (id);

