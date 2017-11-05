#create schema School;

#create table Person(
#	person_id	int,
#    person_name varchar(25)
#);

#drop table Person;

create table Users(
	user_id			int unsigned not null auto_increment,
    username 		varchar(15) not null,
    password		varchar(15) not null,
    last_modified	date,
    constraint 	usr_usr_id_username_pk primary key (user_id, username),
    constraint 	usr_username_uk unique (username)
);

insert into Users values(null, "david.parr", "school1", curdate());
insert into Users values(null, "tomcat", "tomcat", curdate());

create table UserRoles(
	userrole_id		int unsigned not null auto_increment,
    username		varchar(15) not null,
    rolename		varchar(15) not null,
    last_modified	date,
    constraint		usrr_usrr_id_username_rolename_pk primary key(userrole_id, username, rolename),
    constraint		usrr_username_uk unique (username)
);

insert into UserRoles values(null, "david.parr", "admin", curdate());
insert into UserRoles values(null, "tomcat", "school-admin", curdate());

create table Roles(
	role_id 		int unsigned not null auto_increment,
    rolename		varchar(15) not null,
    last_modified	date,
    constraint	rol_rol_id_pk primary key(role_id),
    constraint	rol_rolname_uk unique (rolename)
);

insert into Roles values(null, "admin", curdate());
insert into Roles values(null, "student", curdate());
insert into Roles values(null, "school-admin", curdate());

create table Students(
	student_id		int unsigned not null auto_increment,
    user_id			int unsigned not null,
    role_id			int unsigned not null,
    first_name		varchar(25) not null,
    last_name		varchar(25) not null,
    email			varchar(100) not null,
    last_modified 	date,
    constraint std_std_id_pk primary key (student_id),
    constraint std_user_id_fk foreign key (user_id) references Users (user_id),
    constraint std_role_id_fk foreign key (role_id) references Roles (role_id)
);

create table Courses(
	course_id		int unsigned not null auto_increment,
    course_code		varchar(10) not null,
    course_name		varchar(25) not null,
    last_modified	date,
    constraint crs_crs_id_pk primary key (course_id),
    constraint crs_course_name_uk unique (course_code)
);

create table Semesters(
	semester_id		int unsigned not null,
    semester		varchar(10) not null,
    last_modified	date,
    constraint sem_sem_id_pk primary key (semester_id),
    constraint sem_sem_id_ck check (semester_id >= 1 and semester_id <= 4)
);


insert into Semesters values (1, "Summer", curdate());
insert into Semesters values (2, "Fall", curdate());
insert into Semesters values (3, "Winter", curdate());
insert into Semesters values (4, "Spring", curdate());


create table Results(
	result_id		int unsigned not null auto_increment,
    student_id		int unsigned,
    course_id		int unsigned,
    marks			int,
    semester_id		int unsigned,
    last_modified	date,
    constraint rslt_rslt_id_pk primary key (result_id),
    constraint rslt_std_id_fk foreign key (student_id) references Students (student_id),
    constraint rslt_crs_id_fk foreign key (course_id) references Courses (course_id),
    constraint rslt_sem_id_fk foreign key (semester_id) references Semesters (semester_id),
    constraint rslt_marks_ck check (marks >= 0 and marks <= 100)
);

#drop table Results;
#drop table Semesters;
#drop table Courses;
#drop table Students;
#drop table Users;
#drop table Roles;
#drop table UserRoles;

commit;

create view studentview as
select std.student_id, usr.username, std.user_id, std.role_id, usr.password, rol.rolename, std.first_name, std.last_name, std.email 
from Students std join Users usr
on std.user_id = usr.user_id
join Roles rol
on std.role_id = rol.role_id;

select * from studentview;

