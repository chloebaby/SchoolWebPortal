package util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Role;
import model.Semester;
import model.User;
import model.UserRole;
import service.RoleServiceInterface;
import service.SemesterServiceInterface;
import service.UserRoleServiceInterface;
import service.UserServiceInterface;

public class InitHibernate {
	private static ClassPathXmlApplicationContext ctx;
	private static RoleServiceInterface roleService;
	private static UserRoleServiceInterface userRoleService;
	private static UserServiceInterface userService;
	private static SemesterServiceInterface semesterService;
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		roleService = (RoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_ROLESERVICE);
		userRoleService = (UserRoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERROLESERVICE);
		userService = (UserServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERSERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
		
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		UserRole userRole = new UserRole();
		User user = new User();
		
		Role roleSchoolAdmin = new Role();
		Role roleStudent = new Role();
	
		Semester semesterSummer = new Semester();
		Semester semesterFall = new Semester();
		Semester semesterWinter = new Semester();
		
		roleSchoolAdmin.setRolename("school-admin");
		roleSchoolAdmin.setLastModified(date);
		
		roleStudent.setRolename("student");
		roleStudent.setLastModified(date);
		
		user.setUsername("tomcat");
		user.setPassword("tomcat");
		user.setLastModified(date);
		
		userRole.setUsername("tomcat");
		userRole.setRolename("school-admin");
		userRole.setLastModified(date);
		
		semesterSummer.setSemester("Summer");
		semesterFall.setSemester("Fall");
		semesterWinter.setSemester("Winter");
		
		//Insert the userRole
		userRoleService.saveUserRole(userRole);
		
		//Insert the role
		roleService.saveRole(roleSchoolAdmin);
		roleService.saveRole(roleStudent);
		
		//Insert the user
		userService.saveUser(user);
		
		//Insert the semesters
		semesterService.saveSemester(semesterSummer);
		semesterService.saveSemester(semesterFall);
		semesterService.saveSemester(semesterWinter);

	}

}
