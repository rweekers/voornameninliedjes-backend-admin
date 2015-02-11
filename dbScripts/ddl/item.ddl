create table item (id integer PRIMARY KEY DEFAULT nextval('item_id_seq') 
, title varchar(50) not null
, story text not null
, user varchar(50) not null
, type varchar(50) not null
, status varchar(50) not null
);
