create table searchInstruction (id integer PRIMARY KEY DEFAULT nextval('search_id_seq') 
, argument varchar(255) not null
, ipAddress varchar(50) 
, date_inserted timestamp not null);