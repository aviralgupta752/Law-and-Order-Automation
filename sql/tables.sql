create table if not exists
	administrator (pd_id varchar(45));
create table if not exists
	police_dept(pd_id varchar(45), pd_city varchar(45), pd_area varchar(45), po_id varchar(45), app_id varchar(45), crim_id varchar(45), fir_id varchar(45));

	CREATE TABLE if not exists
	fir(    
	   "FIR_ID" INT not null primary key
	        GENERATED ALWAYS AS IDENTITY
	        (START WITH 1, INCREMENT BY 1),
	   "FIR_NAME" VARCHAR(45),
	   "FIR_FNAME" VARCHAR(45),
	   "FIR_EMAIL" VARCHAR(45),
	   "FIR_CONTACT" VARCHAR(45),
	   "FIR_DOI" VARCHAR(45),
		 "FIR_POI" VARCHAR(45),
		 "FIR_DEP" VARCHAR(45),
		 "FIR_DESC" VARCHAR(45),
	);

create table if not exists
	police_officer(po_id varchar(45), po_name varchar(45), po_dob varchar(45), po_email varchar(45), po_contact varchar(45), po_address varchar(45), po_gender varchar(45), po_dep varchar(45));
create table if not exists
	criminal(crim_id varchar(45), crim_desc varchar(45), crim_name varchar(45), crim_age varchar(45), crim_pd varchar(45), pd_id varchar(45));
create table if not exists
	applic(app_id varchar(45), app_type varchar(45), app_userinfo varchar(45), app_officer varchar(45), po_id varchar(45));
