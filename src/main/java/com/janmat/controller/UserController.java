package com.janmat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janmat.dao.PollDao;
import com.janmat.dao.UserDao;
import com.janmat.model.Poll;
import com.janmat.model.User;


    public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String REGISTER_USER = "/registerUser.html";
    

    private UserDao dao;
    private PollDao pollDao;
    
    public UserController() {
        super();
        dao = new UserDao();
        pollDao = new PollDao();
    }
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User currentUser = (User) request.getSession().getAttribute("loginuser");
		System.out.println("UserController  User " +currentUser);
		int useridno = currentUser.getUserid();
		
        String forward="/myProfile.jsp";
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String email = request.getParameter("email").trim();
        User thisUser = new User() ;
        thisUser.setUserid(useridno);
        thisUser.setFirstName(firstName);
        thisUser.setLastName(lastName);
        thisUser.setEmail(email);
        dao.updateUser(thisUser);   
        User updatedUser = new User();
        updatedUser = dao.getUserById(useridno);
        request.getSession().setAttribute("loginuser", updatedUser );
        
        response.sendRedirect(request.getContextPath() + forward); 
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
