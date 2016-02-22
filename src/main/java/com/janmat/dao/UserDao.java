package com.janmat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.janmat.model.User;
import com.janmat.util.DbUtil;

public class UserDao {

    private Connection connection;

    public UserDao() {
	System.out.println("Establishing Connection to MYSQL database");
        connection = DbUtil.getConnection();
	System.out.println("Connection established");
    }

	public void addUser(User user) {
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("insert into hodusers(firstname,lastname,phone,email,password) values (?,?, ?, ?, ? )");
		    // Parameters start with 1 
		    preparedStatement.setString(1, user.getFirstName());
		    preparedStatement.setString(2, user.getLastName());
		    preparedStatement.setString(3, user.getPhone());
		    preparedStatement.setString(4, user.getEmail());   
    		    preparedStatement.setString(5, user.getPassword());
		    preparedStatement.executeUpdate();
		    System.out.println("User Added");

		} catch (SQLException e) {
			System.out.println("User Not Added");
		    e.printStackTrace();
		}
	}

	public void deleteUser(int userId) {
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("delete from hodusers where userid=?");
		    // Parameters start with 1
		    preparedStatement.setInt(1, userId);
		    preparedStatement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
    }

	public void updateUser(User user) {
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("update hodusers set firstname=?, lastname=?, email=?" + "where userid=?");
		    // Parameters start with 1
		    preparedStatement.setString(1, user.getFirstName());
		    preparedStatement.setString(2, user.getLastName());
		    preparedStatement.setString(3, user.getEmail());
		    preparedStatement.setInt(4, user.getUserid());
		    preparedStatement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
    }
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
		    Statement statement = connection.createStatement();
		    ResultSet rs = statement.executeQuery("select hd.userid, hd.firstname,hd.lastname,hd.phone,hd.email,hd.profileImage ,(select COUNT(*) from polldata pd where pd.createdby=hd.userid) pollsCreated , (select COUNT(*) from ratings r where r.ratedby=hd.userid) pollsRated from hodusers hd;");
		    while (rs.next()) {
		        User user = new User();
		        user.setUserid(rs.getInt("hd.userid"));
		        user.setFirstName(rs.getString("hd.firstname"));
		        user.setLastName(rs.getString("hd.lastname"));
		        user.setPhone(rs.getString("hd.phone"));
		        user.setEmail(rs.getString("hd.email"));
		        user.setProfileImage(rs.getString("hd.profileImage"));
		        user.setPollsCreated(rs.getInt("pollsCreated"));
		        user.setPollsRated(rs.getInt("pollsRated"));
		        users.add(user);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return users;
	}

	
	public User getUserById(int userId) {
		User user = new User();
		try {
		    PreparedStatement preparedStatement = connection.
		            prepareStatement("select hd.userid, hd.firstname, hd.lastname, hd.phone, hd.email,hd.profileImage, (select COUNT(*) from polldata pd where pd.createdby=hd.userid) pollsCreated , (select COUNT(*) from ratings r where r.ratedby=hd.userid) pollsRated from hodusers hd where hd.userid=?;");
		    preparedStatement.setInt(1, userId);
		    ResultSet rs = preparedStatement.executeQuery();

		    if (rs.next()) {
		        user.setUserid(rs.getInt("userid"));
		        user.setFirstName(rs.getString("firstname"));
		        user.setLastName(rs.getString("lastname"));
		        user.setPhone(rs.getString("phone"));
		        user.setEmail(rs.getString("email"));
		        user.setProfileImage(rs.getString("profileImage"));
		        user.setPollsCreated(rs.getInt("pollsCreated"));
		        user.setPollsRated(rs.getInt("pollsRated"));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return user;
    }

	public User authenticateUser(String username, String password) {
		User user = new User();
		System.out.println("Inside the authenticate user method");

		try {
		    PreparedStatement preparedStatement = connection.
		    prepareStatement("select hd.userid, hd.firstname, hd.lastname, hd.phone, hd.email, hd.profileimage,(select COUNT(*) from polldata pd where pd.createdby=hd.userid) pollsCreated , (select COUNT(*) from ratings r where r.ratedby=hd.userid) pollsRated from hodusers hd where email=? and password=?");
		    preparedStatement.setString(1, username);
		    preparedStatement.setString(2, password);
		    ResultSet rs = preparedStatement.executeQuery();

		    if (rs.next()) {
			//System.out.println("Result set is not null" +rs);
		        user.setUserid(rs.getInt("hd.userid"));
		        user.setFirstName(rs.getString("hd.firstname"));
		        user.setLastName(rs.getString("hd.lastname"));
		        user.setPhone(rs.getString("hd.phone"));
		        user.setEmail(rs.getString("hd.email"));
		        user.setProfileImage(rs.getString("profileimage"));
		        user.setPollsCreated(rs.getInt("pollsCreated"));
		        user.setPollsRated(rs.getInt("pollsRated"));
		    }
		   
	
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return user;
    }
	
	
	public void changeProfileiImage(String imgPath, int userId) {
		
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("update hodusers set profileimage=? where userid=?");
		    // Parameters start with 1
		    preparedStatement.setString(1, imgPath);
		    preparedStatement.setInt(2, userId);
		    preparedStatement.executeUpdate();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
    }


}
