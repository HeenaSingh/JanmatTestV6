package com.janmat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.janmat.model.PasswordToken;
import com.janmat.model.Poll;
import com.janmat.model.User;
import com.janmat.util.DbUtil;

public class PasswordTokenDao {
	
    private Connection connection;

    public PasswordTokenDao() {
        connection = DbUtil.getConnection();
        
    }
    
    public String findUserByEmail(String userEmail) {
    	String userExists = "no";
		try {
		    PreparedStatement preparedStatement = connection.prepareStatement("select * from hodusers where email = ?");
		    preparedStatement.setString(1, userEmail);
		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next()) {
		    	userExists = "yes";		    	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExists;
	}
    
	public void addToken(PasswordToken passwordToken) {
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("insert into PasswordToken(passToken,email,timestamp,timeout) values (?,?,current_timestamp ,(current_timestamp + interval 10 minute))");
		    // Parameters start with 1 
		    preparedStatement.setString(1, passwordToken.getPassToken());
		    preparedStatement.setString(2, passwordToken.getEmail());
		    preparedStatement.executeUpdate();
		    System.out.println("TOKEN ADDED");

		} catch (SQLException e) {
			System.out.println("TOKEN NOT ADDED");
		    e.printStackTrace();
		}
	}
	
	public String verifyToken(String uniqueKey) {
		String tokenVerified= "false";
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("select timestamp from PasswordToken where passToken = ? and current_timestamp < timeout ");
		    preparedStatement.setString(1, uniqueKey);
		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next()) {
		    	tokenVerified = "true";
		    	System.out.println("TOKEN VERIFIED");		    	
		    }
		    
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return tokenVerified;
		
	}

	
	public void deleteToken(String userEmail){
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("delete from PasswordToken where email = ?" );
		    preparedStatement.setString(1, userEmail);
		    preparedStatement.executeUpdate();
		   	System.out.println("TOKEN DELETED");		    	
		    
		} catch (SQLException e) {
				e.printStackTrace();
			}	
		
	}
	
	public void changePassword(String userEmail,String newPassword){
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("update hodusers set password = ? where email = ?" );
		    preparedStatement.setString(1, newPassword);
		    preparedStatement.setString(2, userEmail );
		    preparedStatement.executeUpdate();
		    	System.out.println("PASSWORD CHANGED");		    	
		    
		} catch (SQLException e) {
				e.printStackTrace();
		}	
	}
	
	public String findUserEmailByToken(String uniqueKey){
		String userEmail = null;
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("select email from PasswordToken where passToken = ?" );
		    preparedStatement.setString(1, uniqueKey);
		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next()) {
		    	userEmail = rs.getString("email");
		     	System.out.println("EMAIL FETCHED");	    	
		    }
		  		    			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return userEmail;
		
	}
}
