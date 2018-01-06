package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Result;
import service.CourseServiceInterface;
import service.ResultServiceInterface;
import service.StudentServiceInterface;
import util.Constants;

@SuppressWarnings("serial")
public class ResultController extends HttpServlet{
	private ResultServiceInterface resultService;
	private StudentServiceInterface studentService;
	private CourseServiceInterface courseService;
	private ClassPathXmlApplicationContext ctx;
	
	public ResultController() {
		super();
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		resultService = (ResultServiceInterface)ctx.getBean(Constants.SPRING_BEAN_RESULTSERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter(Constants.REQUEST_PARAMETER_OPTION);
		
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)) {
			int mark = Integer.valueOf(request.getParameter(Constants.REQUEST_PARAMETER_MARK));
			UUID resultId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_RESULTID));
			
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			
			Result result = resultService.findResultById(resultId);
			
			result.setMarks(mark);
			result.setLastModified(date);
			
			resultService.updateResult(result);
			
		}
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLRESULTS, resultService.findAllResults());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_RESULTPAGE).forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
		
		if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_EDIT)) {
			doEdit(request, response);
		}else {
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLRESULTS, resultService.findAllResults());
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
			getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_RESULTPAGE).forward(request, response);
		}
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID resultId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_RESULTID));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_RESULT, resultService.findResultById(resultId));
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_RESULTMODIFYPAGE).forward(request, response);
	}
}
