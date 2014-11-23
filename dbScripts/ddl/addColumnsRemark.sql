alter table remark add column visit_id integer REFERENCES visit(id);
alter table remark add column song_id integer REFERENCES song(id);