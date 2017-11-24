create view studentview as
select std.student_id, usr.username, std.user_id, std.role_id, usr.password, rol.rolename, std.first_name, std.last_name, std.email 
from Students std join Users usr
on std.user_id = usr.user_id
join Roles rol
on std.role_id = rol.role_id;



select * from studentview;

select * from Person;

drop table Person;

create table Person(
	person_id		int,
    first_name		varchar(15),
    last_name		varchar(15),
    salary			int
);

select * from students;

select * from users;
select * from userroles;
select * from roles;

select UUID() from roles;
describe roles;

select * from hiberante_sequence;

