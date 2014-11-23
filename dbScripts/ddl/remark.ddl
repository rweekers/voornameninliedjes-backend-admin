create table remark (id integer PRIMARY KEY DEFAULT nextval('remark_id_seq') 
, background text
, youtube varchar(255)
, commentary text not null
, date timestamp not null 
, email varchar(255)
, observer varchar(255)
, status varchar(255));
