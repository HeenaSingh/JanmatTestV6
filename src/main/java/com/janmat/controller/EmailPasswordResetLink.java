package com.janmat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.janmat.dao.PasswordTokenDao;
import com.janmat.model.PasswordToken;


public class EmailPasswordResetLink extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String SENT_SUCCESS = "/resetLinkEmailed.html";
	private static String INCORRECT_EMAIL = "/incorrectEmail.html"; 
			
	
	private PasswordTokenDao passwordTokenDao;
	
    public EmailPasswordResetLink() {
        super();
        passwordTokenDao = new PasswordTokenDao();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("INSIDE THE SENDRESETEMAIl CLASS");
       String forward = null;
		String action = request.getParameter("submit");
			    
        if (action.equalsIgnoreCase("Continue")){
        	String userEmail = request.getParameter("email");
        	if(passwordTokenDao.findUserByEmail(userEmail).equalsIgnoreCase("yes")){
        		String uniqueKey = UUID.randomUUID().toString();   
        		System.out.println (uniqueKey); 
        		String domain = "http://localhost:8080/Rato";
       		    URL url = new URL(domain + "/ResetPasswordPage?uniqueKey=" + uniqueKey);
       		    String to = userEmail;
       		    
       		    /* add this email and token to passwordToken table */
	       		PasswordToken newpasswordToken = new PasswordToken();
	 		    newpasswordToken.setEmail(userEmail);
	 		    newpasswordToken.setPassToken(uniqueKey);
	 		    passwordTokenDao.addToken(newpasswordToken);
	
        		/* Get session object */
       		     
	       		 Properties prop = new Properties();
	             InputStream inputStream = EmailPasswordResetLink.class.getClassLoader().getResourceAsStream("/mail.properties");
	             prop.load(inputStream);		
	            
	             final String HOST =  "mail.smtp.host" ;
	             final String FROM =  prop.getProperty("from") ;
	             final String SFPORT =  "mail.smtp.socketFactory.port" ;
	             final String SOCKET_FACTORY = "mail.smtp.socketFactory.class" ;
	             final String AUTH = "mail.smtp.auth" ;
	             final String PASS = prop.getProperty("pass");
	             final String PORT = "port" ;
	             final String PROTOCOL = "mail.transport.protocol";
	             final String ENABLE = "mail.smtp.ssl.enable";
	             final String DEBUG = "mail.debug" ;
	             
	             prop.setProperty(HOST , prop.getProperty("host"));
	             prop.setProperty(SOCKET_FACTORY, prop.getProperty("socketFactory"));
	       		 prop.setProperty(AUTH, prop.getProperty("auth"));
	       	     prop.setProperty(PORT,prop.getProperty("port"));
	       	     prop.setProperty(SFPORT,prop.getProperty("sfport")); 
	       	     prop.setProperty(PROTOCOL, prop.getProperty("protocol"));
	       	     prop.setProperty(ENABLE, prop.getProperty("enable"));
	       	     prop.setProperty(DEBUG, prop.getProperty("debug"));
	       	    
	       	     		     		     
	       	     Session session = Session.getDefaultInstance(prop,
       		  		 new javax.mail.Authenticator()
	              { protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(FROM,PASS); } 
	       		     });
        			
	       	     /* Create the email content and send to the user */
	    		     
	    	     try{
	    	    	 System.out.println("creatimg mime message");
	    	    	 MimeMessage message = new MimeMessage(session);
	    	    	 message.setFrom(new InternetAddress(FROM));
	    	    	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	   		    	 message.setSubject("Hello there,");
	   		    	 message.setText("Please click here to reset password --" + url);
	   		    	 Transport.send(message);  
	   		         System.out.println("message sent successfully....");  
	  		         forward = SENT_SUCCESS;
	    		  }catch (MessagingException mex) {
	   		    	  mex.printStackTrace(); 
	    		  } 
        	}
            else  if (passwordTokenDao.findUserByEmail(userEmail).equalsIgnoreCase("no")){
            	forward = INCORRECT_EMAIL;
            }
	}
    	
        response.sendRedirect(request.getContextPath() + forward);
	}
		  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
