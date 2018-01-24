package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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

import model.Course;
import model.Result;
import model.Semester;
import service.CourseServiceInterface;
import service.ResultServiceInterface;
import service.StudentServiceInterface;
import util.Constants;

@Controller
public class ResultController{
	private ResultServiceInterface resultService;
	private StudentServiceInterface studentService;
	private CourseServiceInterface courseService;
	private ClassPathXmlApplicationContext ctx;
	
	public ResultController() {
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		resultService = (ResultServiceInterface)ctx.getBean(Constants.SPRING_BEAN_RESULTSERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public ModelAndView getResultPage() {
		return getResultsModel();
	}
	
	@RequestMapping(value="/result/edit/{resultId}", method=RequestMethod.GET)
	public ModelAndView getResultUpdatePage(@PathVariable(Constants.REQUEST_PATH_VARIABLE_RESULTID) String id) {
		UUID resultId = UUID.fromString(id);
		return getResultUpdateModel(resultId);
	}
	
	@RequestMapping(value="/result/update", method=RequestMethod.POST)
	public ModelAndView updateResult(@ModelAttribute(Constants.REQUEST_ATTRIBUTE_RESULT) Result result, @RequestParam(Constants.REQUEST_PARAMETER_MARK) String markParameter ,@RequestParam(Constants.REQUEST_PARAMETER_OPTION) String optionSubmit) {
		String option = optionSubmit;
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)) {
			int mark = Integer.valueOf(markParameter);
			UUID resultId = result.getResultId();
			result = resultService.findResultById(resultId);
			
			result.setMarks(mark);
			result.setLastModified(generateLastModifiedDate());
			
			resultService.updateResult(result);
		}
		
		return getResultsModel();
	}
	
	private ModelAndView getResultsModel() {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_RESULTPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLRESULTS, resultService.findAllResults());
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		
		return model;
	}
	
	private ModelAndView getResultUpdateModel(UUID resultId) {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_RESULTMODIFYPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_RESULT, resultService.findResultById(resultId));
		
		return model;
	}
	
	private java.sql.Date generateLastModifiedDate(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
/*	@Override
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
	}*/
}
