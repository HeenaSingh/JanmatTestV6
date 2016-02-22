package com.janmat.filters;

import java.io.IOException;
import java.util.Enumeration;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
 
/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
public class RequestLoggingFilter implements Filter {
	
	 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
    	
    	this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Request logging Filter : Started ") ;
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
        	System.out.println("Request logging Filter : Has more elements") ;
            String name = params.nextElement();
            String value = request.getParameter(name);
             this.context.log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
        }
        
        System.out.println(params);
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
        	System.out.println("cookie is not null") ;
            for(Cookie cookie : cookies){
                this.context.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
            }
        }
        System.out.println("Request logging Filter :pass the request along the filter chain");
        chain.doFilter(request, response);
        System.out.println("Request logging Filter  : Request passed ") ;
    }
 
    public void destroy() {
        //we can close resources here
    }
 
}