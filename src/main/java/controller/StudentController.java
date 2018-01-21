package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Role;
import model.Student;
import model.User;
import model.UserRole;
import service.RoleServiceInterface;
import service.StudentServiceInterface;
import service.UserRoleServiceInterface;
import util.Constants;

@Controller
public class StudentController{
	private StudentServiceInterface studentService;
	private RoleServiceInterface roleService;
	private UserRoleServiceInterface userRoleService;
	private ClassPathXmlApplicationContext ctx;
	
	public StudentController(){
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		roleService = (RoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_ROLESERVICE);
		userRoleService = (UserRoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERROLESERVICE);
	}
	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public ModelAndView getStudentPage() {
		return getStudentsModel();
	}
	
	@RequestMapping(value="/student/create", method=RequestMethod.POST)
	public ModelAndView createStudent(@ModelAttribute("student") Student student, @ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
		boolean update = false;
		UserRole userRole = buildUserRole(role, user, update);
		Student buildStudent = buildStudent(student, role, user);
		
		studentService.saveStudent(buildStudent);
		userRoleService.saveUserRole(userRole);
		
		return getStudentsModel();
	}
	
	@RequestMapping(value="/student/edit/{studentId}", method=RequestMethod.GET)
	public ModelAndView getStudentUpdatePage(@PathVariable("studentId") String id) {
		UUID studentId = UUID.fromString(id);
		return getStudentUpdateModel(studentId);
	}
	
	@RequestMapping(value="/student/update", method=RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("student") Student student, @ModelAttribute("user") User user, @ModelAttribute("role") Role role, @RequestParam("option") String optionSubmit) {
		String option = optionSubmit;
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)){
			boolean update = true;
			UserRole userRole = buildUserRole(role, user, update);
			student = buildStudent(student, role, user);
			studentService.updateStudent(student);
			userRoleService.updateUserRole(userRole);
		}

		
		return getStudentsModel();
	}
	
	@RequestMapping(value="/student/delete/{studentId}/{username}", method=RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable("studentId") String id, @PathVariable("username") String username) {
		UUID studentId = UUID.fromString(id);
		userRoleService.deleteUserRolebyUsername(username);
		studentService.deleteStudentById(studentId);
		
		return getStudentsModel();
	}
	
	private UserRole buildUserRole(Role role, User user, boolean update) {
		UserRole userRole = null;
		if(update == false) {
			userRole = new UserRole();
			userRole.setUsername(user.getUsername());
		}else {
			userRole = userRoleService.findUserRoleByUsername(user.getUsername());
		}
		
		userRole.setRolename(role.getRolename());
		userRole.setLastModified(generateLastModifiedDate());
		return userRole;
	}
	
	private Student buildStudent(Student student, Role role, User user) {
		UUID roleId = roleService.findUUIDByRolename(role.getRolename());
		role.setRoleId(roleId);
		
		user.setLastModified(generateLastModifiedDate());
		student.setUser(user);
		student.setRole(role);
		student.setLastModified(generateLastModifiedDate());
		
		return student;
	}
	
	private ModelAndView getStudentsModel() {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_STUDENTPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
		return model;
	}
	
	private ModelAndView getStudentUpdateModel(UUID studentId) {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_STUDENTMODIFYPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_STUDENT, studentService.findStudentById(studentId));
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
		return model;
	}
	
	private java.sql.Date generateLastModifiedDate(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
}
