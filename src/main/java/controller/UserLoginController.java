package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import daoimplementation.DAOUserImplementation;

public class UserLoginController extends HttpServlet {
	private UserDAO daoUserImplmenetation;
	
	public UserLoginController() {
		super();
		daoUserImplmenetation = new DAOUserImplementation();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
}
