create table suggestion (id integer PRIMARY KEY DEFAULT nextval('suggestion_id_seq') 
, artist varchar(255) not null
, title varchar(255) not null
, background text
, youtube varchar(255)
, comment text not null
, date timestamp not null 
, email varchar(255)
, observer varchar(255)
, response text
, responsedate timestamp
, status varchar(255)
, visit_id integer REFERENCES visit(id)
, song_id integer REFERENCES song(id)
);
