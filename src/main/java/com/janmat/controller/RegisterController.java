package com.janmat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.janmat.dao.PollDao;
import com.janmat.dao.UserDao;
import com.janmat.model.User;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String THANK_YOU = "/thankyouforRegistering.html";
 
	private UserDao dao;
	
	    
	public RegisterController() {
	    super();
	    dao = new UserDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String forward="";
        String action = request.getParameter("register");
        System.out.println(action);
        if (action.equalsIgnoreCase("Register")){
        	
        	System.out.println(" Got the form details");
        	String firstname = request.getParameter("firstname");
		    String lastname = request.getParameter("lastname");	
        	String email = request.getParameter("email");
		    String password = request.getParameter("password");	
		    String phone=null;
		    User newUser = new User();
		    newUser.setFirstName(firstname);
		    newUser.setLastName(lastname);
		    newUser.setEmail(email);
		    newUser.setPhone(phone);
		    newUser.setPassword(password);
		    dao.addUser(newUser);
		    forward= THANK_YOU;
		    
        }
        response.sendRedirect(request.getContextPath() + forward);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
		
	}
	
}
