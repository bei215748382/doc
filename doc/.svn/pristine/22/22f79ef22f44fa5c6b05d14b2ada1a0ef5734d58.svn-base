/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-7-2 14:55:28                            */
/*==============================================================*/


drop table if exists Delivery_address;

drop table if exists advantisement;

drop table if exists area_table;

drop table if exists book;

drop table if exists book_classification;

drop table if exists book_comment;

drop table if exists book_homepage_showing;

drop table if exists cosumer;

drop table if exists indent;

drop table if exists indent_details;

drop table if exists manager;

drop table if exists shoppingcart;

/*==============================================================*/
/* Table: Delivery_address                                      */
/*==============================================================*/
create table Delivery_address
(
   delivery_address_id  int not null auto_increment,
   user_id              int not null,
   province_name        varchar(10) not null comment '收货地址中的省名',
   province_id          int not null comment '省ID',
   city_name            varchar(10) not null comment '收货地址中的市名',
   city_id              int not null comment '市ID',
   district_name        varchar(10) not null comment '收货地址中的区或县名',
   district_id          int not null comment '区或县ID',
   detail_address       varchar(100) not null comment '收货地址中剩下的地址',
   postcode             int comment '收货地址邮编',
   connect_number       int not null comment '收获人的联系电话',
   consignee_name       varchar(60) not null,
   acquiesce            bool not null comment '是否默认为收货地址',
   primary key (delivery_address_id)
);

alter table Delivery_address comment '用户的收货地址';

/*==============================================================*/
/* Table: advantisement                                         */
/*==============================================================*/
create table advantisement
(
   ad_id                int not null auto_increment comment '广告Id',
   ad_name              varchar(50) not null comment '广告名称',
   simple_depict        varchar(100) comment '广告的简单描述',
   detail_depict        text comment '广告的详细描述',
   url                  varchar(50) not null comment '广告的url地址',
   image                varchar(50) comment '广告的图片',
   click_times          int not null comment '广告的点击次数',
   ad_show              bool not null comment '广告现在是否展示出来',
   release_time         datetime not null comment '广告的发布时间',
   end_time             datetime not null comment '发布时间',
   build_time           datetime not null comment '创建时间',
   primary key (ad_id)
);

alter table advantisement comment '广告相关信息';

/*==============================================================*/
/* Table: area_table                                            */
/*==============================================================*/
create table area_table
(
   area_id              int not null auto_increment comment '区域ID',
   area_name            varchar(60) not null comment '区域名称',
   one_level            int not null comment '一级的区域',
   two_level            int not null comment '二级的区域',
   three_level          int not null comment '三级的区域',
   rank                 int not null comment '排序',
   primary key (area_id)
);

alter table area_table comment '区域表';

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   book_id              int not null auto_increment comment '书的编号',
   book_name            varchar(60) not null comment '书本的名字',
   book_retailprice     double precision(10,2) not null comment '书在实体店的售价',
   book_price           double precision(10,2) not null comment '书在网站上售卖的价格',
   book_storeamont      int not null comment '书的库存量',
   buyinglimiti_amount  int not null comment '限购的数量',
   book_press           varchar(50) not null comment '出版社的名字',
   book_authorname      varchar(60) not null comment '作者的名字',
   book_point           int comment '卖书可以获得的积分',
   if_on_sale           bool not null comment '书是否上架',
   book_comments        varchar(100) not null comment '书本的简介',
   book_details         text not null comment '书的详细信息',
   book_pictures        varchar(60) not null comment '图片',
   book_publish_time    datetime not null comment '出版时间',
   on_sale_time         datetime not null comment '上架的时间',
   primary key (book_id)
);

alter table book comment '网站售卖的商品，即为书';

/*==============================================================*/
/* Table: book_classification                                   */
/*==============================================================*/
create table book_classification
(
   classification_id    int not null auto_increment comment '分类的Id ',
   classification_name  varchar(15) not null comment '分类的名称',
   class_first          int not null comment '一级分类',
   class_second         int not null comment '二级分类',
   class_third          int not null comment '三级分类',
   reorder_number       int not null comment '排序编号',
   primary key (classification_id)
);

alter table book_classification comment '商品分类的信息';

/*==============================================================*/
/* Table: book_comment                                          */
/*==============================================================*/
create table book_comment
(
   comment_id           int not null auto_increment comment '评论的编号',
   cosumer_d            int not null comment '用户编号',
   comment_name         varchar(20) not null comment '评论人的名字',
   comment_level        int not null comment '评论的等级',
   comment_contains     varchar(300) not null comment '用户评论的内容',
   comment_time         datetime not null comment '评论的时间',
   reply_contains       varchar(300) not null comment '网站回复的内容',
   reply_time           datetime not null comment '网站回复的时间',
   order_id             int not null comment '这个评论有关的订单明细的Id ',
   book_id              int not null comment '书的编号',
   primary key (comment_id)
);

/*==============================================================*/
/* Table: book_homepage_showing                                 */
/*==============================================================*/
create table book_homepage_showing
(
   showing_id           int not null auto_increment comment '显示的模块的Id
            ',
   showing_name         varchar(60) not null comment '显示模块的名称',
   showing_recoder      int not null comment '显示内容的排序',
   book_id              int not null comment '书的编号
            ',
   primary key (showing_id)
);

alter table book_homepage_showing comment '首页展示的内容';

/*==============================================================*/
/* Table: cosumer                                               */
/*==============================================================*/
create table cosumer
(
   cosumer_id           int not null auto_increment comment '购物车对应的用户编号',
   cosumer_name         varchar(60) not null comment '用户的名字',
   mai_address          varchar(100) not null comment '用户的邮箱',
   cosumer_password     varchar(60) not null comment '用户的登录密码',
   cosumer_points       int comment '用户已有的积分',
   cosumer_sex          smallint comment '用户的性别',
   cosumer_phonenumber  int not null comment '用户联系的电话',
   cosumer_headpicture  varchar(60) comment '用户的头像',
   cosumer_zipcode      int comment '用户所在地的邮编',
   cosumer_address      varchar(60) comment '联系的地址',
   cosumer_condition    smallint not null comment '账户的状态【是否被屏蔽】',
   primary key (cosumer_id)
);

/*==============================================================*/
/* Table: indent                                                */
/*==============================================================*/
create table indent
(
   indent_id            int not null auto_increment,
   indent_state         smallint not null,
   indent_payment_way   smallint not null,
   logistic_way         varchar(20),
   logistic_number      int,
   logistic_fee         double precision(10,2),
   indent_payprice      double precision(10,2) not null,
   indent_oldprice      double precision(10,2),
   user_id              int not null,
   consumer_name        varchar(60) not null,
   consignee_name       varchar(60) not null,
   deliver_address      varchar(60) not null,
   contact_number       int not null,
   postcode             int,
   consumer_remark      varchar(400),
   indent_build_time    datetime not null,
   primary key (indent_id)
);

alter table indent comment '订单里有的信息';

/*==============================================================*/
/* Table: indent_details                                        */
/*==============================================================*/
create table indent_details
(
   id                   int not null auto_increment comment '订单明细的id',
   indent_id            int not null comment '对应的订单id',
   goods_id             int not null comment '商品ID',
   goods_amount         int not null comment '商品数量',
   goods_name           varchar(60) not null comment '商品名称',
   goods_price          double precision(10,2) not null comment '商品单价',
   build_time           datetime not null comment '创建时间',
   goods_image          varchar(100) comment '商品图片',
   primary key (id)
);

alter table indent_details comment '订单中每个商品的详细信息';

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   manager_id           int not null auto_increment comment '管理员ID',
   manager_image        varchar(50) comment '管理员头像',
   manager_number       varchar(60) not null comment '管理员账号',
   manager_code         varchar(60) not null comment '管理员密码',
   super_administrator  bool not null comment '是否为超级管理员',
   stop_administrator   bool not null comment '是否停用管理员',
   primary key (manager_id)
);

alter table manager comment '后台管理员信息表';

/*==============================================================*/
/* Table: shoppingcart                                          */
/*==============================================================*/
create table shoppingcart
(
   shoppingcart_id      int not null auto_increment comment '购物车的编号',
   book_id              int not null comment '书的编号
            ',
   book_amout           int not null comment '要购买书的数量',
   book_name            varchar(60) not null comment '书本的名字',
   book_retailprice     double precision(10,2) not null comment '书在实体店的售价',
   book_price           double precision(10,2) not null comment '书在网站上售卖的价格',
   book_points          int comment '购买商品后可以获得的积分',
   cosumer_id           int not null comment '购物车对应的用户编号',
   addingcart_time      datetime not null comment '书添加到购物车的时间',
   primary key (shoppingcart_id)
);

alter table indent_details add constraint FK_Relationship_1 foreign key (indent_id)
      references indent (indent_id) on delete restrict on update restrict;

