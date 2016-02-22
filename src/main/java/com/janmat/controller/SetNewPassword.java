package com.janmat.controller;

import java.io.IOException;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janmat.dao.PasswordTokenDao;


public class SetNewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PASSWORD_CHANGED = "/loginAgain.html" ;
	private static String INCORRECT_EMAIL = "/incorrectEmail.html";
	
    private PasswordTokenDao passwordTokenDao;
        
    public SetNewPassword() {
        super();
       passwordTokenDao = new PasswordTokenDao() ;
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("INSIDE THE SETNEWPASSWORD CLASS");
	    String forward = "";
	   
	    String uniqueKey = request.getParameter("uKey");
	    System.out.println("UNIQUE KEY IS " +uniqueKey);
	    String newPassword = request.getParameter("newPassword");
	    String userEmail = passwordTokenDao.findUserEmailByToken(uniqueKey);
	    System.out.println("USER EMAIL IS " +userEmail);
	    if (userEmail != null){	    
		    passwordTokenDao.changePassword(userEmail,newPassword);
		    passwordTokenDao.deleteToken(userEmail);
		    forward = PASSWORD_CHANGED ;  
	    }
	    else{
	    	forward = INCORRECT_EMAIL;
	    }
	    response.sendRedirect(request.getContextPath() + forward);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
