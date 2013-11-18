create table songOfTheDay (id integer PRIMARY KEY DEFAULT nextval('songOfTheDay_id_seq') 
, day date not null
, song_id integer not null REFERENCES song(id)
);