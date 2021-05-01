create table if not exists
	administrator (pd_id varchar(45));

	CREATE TABLE if not exists
		police_dept(
		   "PD_ID" INT not null primary key
		        GENERATED ALWAYS AS IDENTITY
		        (START WITH 1, INCREMENT BY 1),
			"PD_NAME" VARCHAR(45),
			"PD_LOC" VARCHAR(45),
		  "PD_CITY" VARCHAR(45),
		  "PD_AREA" VARCHAR(45),
		  "PD_PHONE" VARCHAR(45),
		  "PD_REMARKS" VARCHAR(45)
		);

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
		 "FIR_PS" VARCHAR(45),
		 "FIR_DEP" VARCHAR(45),
		 "FIR_DESC" VARCHAR(200),
		 "FIR_STAT" INT,
		 "FIR_EMAIL_SENT" INT,
		 "FIR_PO_ID" VARCHAR(45),
		 -- FOREIGN KEY ("FIR_PO_ID") REFERENCES police_officer("PO_ID")
	);

CREATE TABLE if not exists
	police_officer(
		"PO_ID" INT not null primary key
			 GENERATED ALWAYS AS IDENTITY
			 (START WITH 1, INCREMENT BY 1),
	   "PO_NAME" VARCHAR(45),
	   "PO_DOB" VARCHAR(45),
	   "PO_GENDER" VARCHAR(45),
	   "PO_DEP" VARCHAR(45),
	   "PO_EMAIL" VARCHAR(45),
		 "PO_CONTACT" VARCHAR(45),
		 "PO_PS" VARCHAR(45),
		 "PO_DRUGS" VARCHAR(45),
		 "PO_AID" VARCHAR(45),
		 "PO_LENSES" VARCHAR(45),
	);
	CREATE TABLE if not exists
		police_officer_list(
		   "USERNAME" VARCHAR(45),
		   "PASSWORD" VARCHAR(45)
		);
create table if not exists
	criminal(crim_id varchar(45), crim_desc varchar(45), crim_name varchar(45), crim_age varchar(45), crim_pd varchar(45), pd_id varchar(45));
create table if not exists
	applic(app_id varchar(45), app_type varchar(45), app_userinfo varchar(45), app_officer varchar(45), po_id varchar(45));
