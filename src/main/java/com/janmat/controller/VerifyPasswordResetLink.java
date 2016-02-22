package com.janmat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janmat.dao.PasswordTokenDao;
import com.janmat.model.PasswordToken;

public class VerifyPasswordResetLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String TIMEOUT_PAGE = "/oopsTimeout.html";
	private static String RESET_PASSWORD ;
       
    private PasswordTokenDao passwordTokenDao;
    
    public VerifyPasswordResetLink() {
        super();
        passwordTokenDao = new PasswordTokenDao() ;
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("INSIDE THE VERIFY NEW PASSWORD CLASS");
	    String forward = "";
	       
	    String uniqueKey = request.getParameter("uniqueKey");
	    
		System.out.println("UNIQUE KEY IS THIS : " + uniqueKey);
		
		if(uniqueKey != null && passwordTokenDao.verifyToken(uniqueKey).equalsIgnoreCase("true")){
			request.setAttribute("uniqueKey", uniqueKey);
			RESET_PASSWORD = "/resetPassword.jsp?uniqueKey=" + uniqueKey; 
			forward = RESET_PASSWORD;
		}
		else {
			forward = TIMEOUT_PAGE;
		}  
		
		response.sendRedirect(request.getContextPath() + forward);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
