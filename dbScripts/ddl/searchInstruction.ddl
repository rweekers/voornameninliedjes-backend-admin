create table searchInstruction (id integer PRIMARY KEY DEFAULT nextval('search_id_seq') 
, argument varchar(255) not null
, ipAddress varchar(50) 
, country varchar(255) 
, browser varchar(255) 
, operatingSystem varchar(255) 
, date_inserted timestamp not null);
