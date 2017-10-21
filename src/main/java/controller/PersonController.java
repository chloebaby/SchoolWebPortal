package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDAO;
import daoimplementation.DAOPersonImplementation;
import model.Person;

public class PersonController extends HttpServlet{
	private PersonDAO daoPersonImplementation;
	
	public PersonController() {
		super();
		daoPersonImplementation = new DAOPersonImplementation();
	}
	
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idPerson = request.getParameter("IDPerson");
		String name = request.getParameter("Name");
		
		daoPersonImplementation.insertPerson(Integer.valueOf(idPerson), name);
		
		List<Person> allPersons = daoPersonImplementation.select();
		
		request.setAttribute("allPersons", allPersons);
		
		getServletContext().getRequestDispatcher("/jsp/person-details.jsp").forward(request, response);
				
	}
}
