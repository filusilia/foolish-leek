-- auto-generated definition
create table ft_fund
(
    id                       int(8) auto_increment comment '主键'
        primary key,
    fund_code                varchar(6)     not null comment '基金代码',
    fund_name                varchar(255)   not null comment '基金名称',
    short_name               varchar(100)   not null comment '基金拼音简称',
    fund_full_pinyin         varchar(255)   not null comment '基金全称',
    fund_type                varchar(100)   null comment '基金类型',
    net_worth                decimal(12, 6) null comment '当前基金单位净值',
    expect_worth             decimal(12, 6) null comment '当前基金单位净值估算',
    total_worth              decimal(12, 6) null comment '当前基金累计净值',
    expect_growth            varchar(10)    null comment '当前基金单位净值估算日涨幅,单位为百分比',
    day_growth               varchar(10)    null comment '单位净值日涨幅,单位为百分比',
    last_week_growth         varchar(10)    null comment '单位净值周涨幅,单位为百分比',
    last_month_growth        varchar(10)    null comment '单位净值月涨幅,单位为百分比',
    last_three_months_growth varchar(10)    null comment '单位净值三月涨幅,单位为百分比',
    last_six_months_growth   varchar(10)    null comment '单位净值六月涨幅,单位为百分比',
    last_year_growth         varchar(10)    null comment '单位净值年涨幅,单位为百分比',
    buy_min                  decimal(20, 6) null comment '最小申购金额',
    buy_source_rate          decimal(12, 6) null comment '原始买入费率,单位为百分比',
    fund_rate                decimal(12, 6) null comment '现费率',
    manager                  varchar(50)    null comment '基金经理',
    create_time              datetime       null comment '创建时间',
    update_time              datetime       null comment '更新时间'
)
    comment '基金表';

create index code
    on ft_fund (fund_code)
    comment '基金代码的索引';

-- auto-generated definition
create table ft_fund_company
(
    id           int(8) auto_increment comment '主键'
        primary key,
    company_code varchar(8)   not null comment '公司代码',
    company_name varchar(255) not null comment '公司名称',
    create_time  datetime     null comment '创建时间',
    update_time  datetime     null comment '更新时间'
);

-- auto-generated definition
create table ft_user
(
    id          int(8) auto_increment comment '主键'
        primary key,
    user_code   int(8)       not null comment '用户编号',
    username    varchar(100) null comment '用户名',
    password    varchar(50)  null comment '密码',
    nickname    varchar(50)  null comment '昵称',
    sex         char(2)      null comment '用户性别（0男 1女 2未知）',
    avatar      varchar(255) null comment '头像',
    email       varchar(150) null comment '邮箱',
    mobile      varchar(20)  null comment '手机号',
    status      char         null comment '用户状态：0-正常，1-已删除',
    create_by   int(8)       null comment '创建者',
    remark      varchar(255) null comment '备注',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间'
)
    comment '用户表';

-- auto-generated definition
create table ft_user_fund
(
    id          int(8) auto_increment comment '主键'
        primary key,
    user_id     int(8)     not null comment '用户id',
    fund_code   varchar(6) not null comment '基金代码',
    favorite    char       null comment '是否收藏：0- 未收藏，1- 收藏',
    sort        int(4)     null comment '排序',
    create_time datetime   null comment '创建时间',
    update_time datetime   null comment '更新时间'
);

create index fund_code
    on ft_user_fund (fund_code)
    comment '基金code';

create index user_id
    on ft_user_fund (user_id)
    comment '用户名';