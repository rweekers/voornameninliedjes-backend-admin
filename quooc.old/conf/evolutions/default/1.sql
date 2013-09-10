# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table song (
  id                        bigint auto_increment not null,
  artist                    varchar(255),
  title                     varchar(255),
  surname                   varchar(255),
  name_index                integer,
  name_length               integer,
  date_inserted             datetime,
  date_modified             datetime,
  user_inserted             varchar(255),
  user_modified             varchar(255),
  constraint pk_song primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table song;

SET FOREIGN_KEY_CHECKS=1;

