create table song (id integer PRIMARY KEY DEFAULT nextval('song_id_seq') 
, artist varchar(255) not null
, title varchar(255) not null
, firstname varchar(50) not null
, name_index int not null
, name_length int not null
, date_inserted timestamp not null 
, date_modified timestamp 
, user_inserted varchar(255) not null
, user_modified varchar(255));
