create table suggestion_song (id integer PRIMARY KEY DEFAULT nextval('suggestion_song_id_seq') 
, suggestion_id integer REFERENCES suggestion(id)
, song_id integer REFERENCES song(id)
);
