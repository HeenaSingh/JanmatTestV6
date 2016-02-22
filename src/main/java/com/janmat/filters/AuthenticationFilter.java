package com.janmat.filters;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	System.out.println("Authentication Filter : Started") ;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        System.out.println("URI is : " +uri);
        this.context.log("Requested Resource::"+uri);
         
        HttpSession session = req.getSession(false);
         
        if(session == null && !(uri.endsWith("html") || uri.endsWith("LoginUser") || uri.endsWith("RegisterUser") || uri.endsWith("SendResetEmail") || uri.endsWith("ResetPasswordPage") || uri.endsWith("resetPassword.jsp"))){
            this.context.log("Unauthorized access request");
            System.out.println("Authentication Filter : Unauthorized access request");
            res.sendRedirect("index.html");
            
        }else{
        	System.out.println("Authentication Filter : Request Passed further") ;
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
         
         
    }
 
      
    public void destroy() {
        //close any resources here
    }
 
}