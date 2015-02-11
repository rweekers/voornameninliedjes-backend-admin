create table item_song (
  item_id integer REFERENCES suggestion(id)
, song_id integer REFERENCES song(id)
);
