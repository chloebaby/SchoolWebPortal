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
    course_name		varchar(25) not null,
    last_modified	date,
    constraint crs_crs_id_pk primary key (course_id)
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

create table Users(
	user_id			int unsigned not null auto_increment,
    user_name		varchar(20) not null,
    first_name		varchar(25) not null,
    last_name		varchar(25) not null,
    user_password	varchar(10) not null,
    email			varchar(100) not null,
    last_modified	date,
    constraint user_user_id_pk primary key (user_id),
    constraint user_user_username_uk unique (user_name)
);

insert into Users(user_id, user_name, first_name, last_name, user_password, email, last_modified)
values (null, "david.parr", "David", "Parr", "school1", "david.parr@hotmail.ca", current_date());
select * from Users;

insert into Students(student_id, first_name, last_name, email, last_modified)
values (null, "David", "Parr", "david.parr@hotmail.ca", current_date());
insert into Students(student_id, first_name, last_name, email, last_modified)
values (null, "Morty", "Smith", "morty.smith@hotmail.ca", current_date());
insert into Students(student_id, first_name, last_name, email, last_modified)
values (null, "Rick", "Sanchez", "rick.sanchez@hotmail.ca", current_date());

select * from Students;
select * from Courses;
select * from Semesters;
select * from Results;
select * from Person;

drop table Students;
drop table Courses;
drop table Semesters;
drop table Results;
drop table Users;

create table Users(
	username varchar(15) not null primary key,
    password varchar(15) not null
);

create table UserRole(
	username varchar(15) not null,
    rolename varchar(15) not null,
    primary key(username, rolename)
);

insert into Users values("david.parr", "school1");
insert into UserRole values("david.parr", "admin");
commit;