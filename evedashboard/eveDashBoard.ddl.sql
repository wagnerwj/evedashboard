
CREATE SCHEMA evedashboard 

CREATE SEQUENCE evedashboard.eve_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100001
  CACHE 1;
ALTER TABLE evedashboard.eve_id_seq
  OWNER TO postgres;
  
  CREATE TABLE evedashboard.eve_pilot(
   eve_pilot_id INT PRIMARY KEY,
   email_address varchar(100),
   hash varchar(100),
   salt varchar(100),
   primary_char INT references evedashboard.eve_character(eve_character_id)
   );
   
   
CREATE TABLE evedashboard.mnds_account (
	mnds_account_id INT PRIMARY KEY,
	name TEXT,
	eve_pilot_id INT references evedashboard.eve_pilot(eve_pilot_id)
);

CREATE TABLE evedashboard.eve_api(
	api_key varchar(10) PRIMARY KEY,
	vcode varchar(50) NOT NULL,
	eve_pilot_id INT references evedashboard.eve_pilot(eve_pilot_id)
);

CREATE TABLE evedashboard.eve_character(
   eve_character_id INT PRIMARY KEY,
   character_name varchar(80),
   avatar_url varchar(100),
   current_corp varchar(100),
   eve_pilot_id INT references evedashboard.eve_pilot(eve_pilot_id)
   );
   
 
 
  