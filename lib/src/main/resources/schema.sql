
create table supervisors(

sup_id serial primary key,
sup_fn varchar not null,
sup_ln varchar not null

);


create table employees(

emp_id serial primary key,
emp_fn varchar not null,
emp_ln varchar not null,
emp_dep varchar not null,
sup_id_fk int not null references supervisors(sup_id) 

);


create table reimbursment(

r_id serial primary key,
emp_id_fk int not null references employees(emp_id),
sup_id_fk int not null references supervisors(sup_id),
r_amt numeric (8,2) not null check (r_amt >0),
r_image varchar ,
r_remarks varchar,
r_status varchar

);