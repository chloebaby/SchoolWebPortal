package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import daoimplementation.DAOUserImplementation;
import model.User;

public class UserLoginController extends HttpServlet {
	private UserDAO daoUserImplmenetation;
	
	public UserLoginController() {
		super();
		daoUserImplmenetation = new DAOUserImplementation();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = daoUserImplmenetation.selectUsernamePasswordByUsername(username);
		
		String returnUsername  = user.getUserName();
		String returnPassword = user.getPassword();
		
		if(returnUsername.equals(username) && returnPassword.equals(password)) {
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
		}
	}
}
