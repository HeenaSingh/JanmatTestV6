package com.janmat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.janmat.dao.PollDao;
import com.janmat.dao.UserDao;
import com.janmat.model.Poll;
import com.janmat.model.User;


public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String REGISTER_USER = "/registerUser.html";
    private static String LIST_USER = "/loginHome.jsp";
    private static String FORGOT_PASS = "/forgotPass.jsp";
   

    private UserDao dao;
    private PollDao pollDao;
    
    public LoginController() {
        super();
        dao = new UserDao();
        pollDao = new PollDao();
    }
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
        String forward="";
        String action = request.getParameter("submit");
        System.out.println(action);
        if (action.equalsIgnoreCase("Login")){
        	String username = request.getParameter("email").trim();
		System.out.println(username);
		    String password = request.getParameter("pass").trim();	    
		    User user = dao.authenticateUser(username,password);
		    if(user.getEmail()!=null){
		    	HttpSession session = request.getSession(true);
		        List<Poll> polls = new ArrayList<Poll>();
	            polls = pollDao.getAllPolls();
	            session.setAttribute("polls", polls);
	            session.setAttribute("loginuser",user);
	            
	            System.out.println("THE USER AUTHENTICATED : " +user);
	            System.out.println("ALL POLLS  ARE: ");
	            System.out.println(polls);
	            
	            forward = LIST_USER;
		    }
		    else{
		    forward = REGISTER_USER;
		    }
        }
        else if(action.equalsIgnoreCase("SignUp")){
            forward = REGISTER_USER;
        }
        else if(action.equalsIgnoreCase("forgotPass")){
            forward = FORGOT_PASS;
        }
	
	response.sendRedirect(request.getContextPath() + forward);
    }
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException {
	    doPost(req, res);
	}
   
}
