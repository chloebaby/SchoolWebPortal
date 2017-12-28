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

select * from STUDENT_RESULT;
select * from SEMESTER_RESULTS;

select * from Semesters;
select * from Courses;
select * from COURSE_RESULT;
select * from STUDENT_COURSE;
select * from SEMESTER_RESULTS;
select * from STUDENT_RESULT;
select * from Results;
select * from Students;

select stu.first_name from Courses crs
join COURSE_STUDENT crs_stu on crs.course_id=crs_stu.course_id
join Students stu on crs_stu.student_id=stu.student_id
join COURSE_SEMESTER crs_sem on crs.course_id = crs_sem.course_id
join Semesters sem on crs_sem.semester_id = crs.course_id;

select * from Semesters;
select * from Courses;
select * from COURSE_SEMESTER;


select sem.semester, stu.first_name from Semesters sem
join COURSE_SEMESTER crs_sem on crs_sem.semester_id = sem.semester_id
join Courses crs on crs_sem.course_id = crs.course_id
join COURSE_STUDENT crs_stu on crs.course_id=crs_stu.course_id
join Students stu on crs_stu.student_id=stu.student_id
where crs.course_id='38a85798-cdc8-4d7b-bc39-d837fc55072f';


select * from Students;
select * from Results;
select * from RESULT_STUDENT;
select * from COURSE_STUDENT;
select * from RESULT_COURSE;
select * from COURSE_SEMESTER;
select * from SEMESTER_RESULTS;
select * from STUDENT_SEMESTER;

select * from Roles;

select stu.first_name, stu.last_name, res.marks
from Students stu
join RESULT_STUDENT res_stu on stu.student_id = res_stu.student_id
join Results res on res_stu.result_id = res.result_id;