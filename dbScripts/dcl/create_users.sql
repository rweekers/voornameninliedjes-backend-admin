create user vil with password 'password';

grant select on table song to vil;
grant update on table song to vil;
grant select on table searchInstruction to vil;
grant insert on table searchInstruction to vil;
grant select on sequence search_id_seq to vil;
grant update on sequence search_id_seq to vil;

grant select on table visit to vil;
grant insert on table visit to vil;
grant select on sequence visit_id_seq to vil;
grant update on sequence visit_id_seq to vil;

grant select on table songOfTheDay to vil;
grant update on table songOfTheDay to vil;
grant insert on table songOfTheDay to vil;
grant select on sequence songOfTheDay_id_seq to vil;
grant update on sequence songOfTheDay_id_seq to vil;

create user vil_cli with password 'password';
grant select on table song to vil_cli;
grant insert on table song to vil_cli;
grant select on sequence song_id_seq to vil_cli;
grant update on sequence song_id_seq to vil_cli;
