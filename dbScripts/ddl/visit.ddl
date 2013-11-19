create table visit (id integer PRIMARY KEY DEFAULT nextval('visit_id_seq') 
, ipAddress varchar(50) 
, country varchar(255) 
, city varchar(255) 
, browser varchar(255) 
, operatingSystem varchar(255) 
, date_inserted timestamp not null);