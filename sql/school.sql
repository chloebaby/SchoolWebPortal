create schema School;

create table Person(
	person_id	int,
    person_name varchar(25)
);

drop table Person;

create table Students(
	student_id		int unsigned not null auto_increment,
    first_name		varchar(25) not null,
    last_name		varchar(25) not null,
    email			varchar(100),
    last_modified 	date,
    constraint std_std_id_pk primary key (student_id),
    constraint std_email_uk unique (email)
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
    constraint sem_sem_id_pk primary key (semester_id),
    constraint sem_sem_id_ck check (semester_id >= 1 and semester_id <= 4)
);

insert into Semesters (semester_id, semester) values (1, "Summer");
insert into Semesters (semester_id, semester) values (2, "Fall");
insert into Semesters (semester_id, semester) values (3, "Winter");
insert into Semesters (semester_id, semester) values (4, "Spring");


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

select * from Students;
select * from Courses;
select * from Semesters;
select * from Results;
select * from Person;
select * from Users;
select * from UserRole;
select * from Roles;

#drop table Students;
#drop table Courses;
#drop table Semesters;
#drop table Results;
#drop table Users;
#drop table UserRole;
#drop table Roles;

#create table Users(
#	username varchar(15) not null primary key,
#    password varchar(15) not null
#);

#create table UserRole(
#	username varchar(15) not null,
#    rolename varchar(15) not null,
#    primary key(username, rolename)
#);

insert into Users values("david.parr", "school1");
insert into Users values("tomcat", "tomcat");
insert into UserRole values("david.parr", "admin");
insert into UserRole values("tomcat" , "admin");
insert into UserRole values("tomcat", "admin-gui");
insert into UserRole values("tomcat", "manager-gui");
insert into UserRole values("tomcat", "programmer");
insert into UserRole values("tomcat", "service");
insert into UserRole values("tomcat", "school-admin");
commit;


create table Users(
	user_id		int unsigned not null auto_increment,
    username 	varchar(15) not null,
    password	varchar(15) not null,
    constraint 	usr_usr_id_username_pk primary key (user_id, username),
    constraint 	usr_username_uk unique (username)
);

create table UserRole(
	userrole_id		int unsigned not null auto_increment,
    username		varchar(15) not null,
    rolename		varchar(15) not null,
    constraint		usrr_usrr_id_username_rolename_pk primary key(userrole_id, username, rolename),
    constraint		usrr_username_uk unique (username)
);

create table Roles(
	role_id 	int unsigned not null auto_increment,
    rolename	varchar(15) not null,
    constraint	rol_rol_id_pk primary key(role_id),
    constraint	rol_rolname_uk unique (rolename)
);

insert into Users values(null, "david.parr", "school1");
insert into Users values(null, "tomcat", "tomcat");

insert into UserRole values(null, "david.parr", "admin");
insert into UserRole values(null, "tomcat", "school-admin");

update UserRole set rolename="school-admin" where username="tomcat";

insert into Roles values(null, "admin");
insert into Roles values(null, "student");
insert into Roles values(null, "school-admin");
commit;