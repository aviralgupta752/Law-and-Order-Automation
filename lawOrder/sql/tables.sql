create table if not exists 
	administrator (pd_id varchar(45));
create table if not exists
	police_dept(pd_id varchar(45), pd_city varchar(45), pd_area varchar(45), po_id varchar(45), app_id varchar(45), crim_id varchar(45), fir_id varchar(45));
create table if not exists
	fir(fir_id varchar(45), fir_desc varchar(45), fir_type varchar(45), po_id varchar(45));
create table if not exists
	police_officer(po_id varchar(45),po_city varchar(45), po_name varchar(45));
create table if not exists
	criminal(crim_id varchar(45), crim_desc varchar(45), crim_name varchar(45), crim_age varchar(45), crim_pd varchar(45), pd_id varchar(45));
create table if not exists
	applic(app_id varchar(45), app_type varchar(45), app_userinfo varchar(45), app_officer varchar(45), po_id varchar(45));