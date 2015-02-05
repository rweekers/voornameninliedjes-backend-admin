create table suggestion_song suggestion_id integer REFERENCES suggestion(id)
, song_id integer REFERENCES song(id)
);
