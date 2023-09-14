package com.curd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDAO 
{
   
   private String JdbcUrl = "jdbc:mysql://localhost:3306/crudoperation?autoReconnect=true&useSSL=false";
   private String JdbcUsername= "root";
   private String JdbcPass = "bugsop";
   
   // yaha hamne sabhi sql ki quary ko likh ke ek variable me store kar diya hai jisse mujhe ye asani hogi ki jab mujhe koi queary chalni hogi direct ham use variable ko use kar lenge or hamne ise static banaya hai take mujhe iskko use karne k liye bar bar object banane ki zaroorat na pade...
   private static final String INSERT_USER = "INSERT INTO userdata " + " (name,email,phone,city) VALUES " + "(?,?,?,?);";
   private static final String SELECT_USER_BY_ID = " SELECT id,name,email,phone,city FROM userdata WHERE id=?";
   private static final String SELECT_ALL_USER = "SELECT * FROM userdata";
   private static final String DELETE_USER = "DELETE FROM userdata WHERE id=?;";
   private static final String UPDATE_USER = "UPDATE userdata SET name=?,email=?,phone=?,city=? WHERE id=?;";
   
   UserDAO()
   {
	   
   }
  
   protected Connection getConnection() // ye hamne database se connection banane k liye method banya hai jab v mujhe database connection ki zarorat hogi ham getConnection ko call kar denge jo ki hamne connection return kar denga...
   {
	   Connection connection = null;
	   
	   try 
	   {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 connection = DriverManager.getConnection(JdbcUrl,JdbcUsername,JdbcPass);
	   } 
	   catch (Exception e) 
	   {
		 e.printStackTrace();
	   }
	   
	   return connection;
	   
   }
   
   
   // Insert User details 
   public void insertUser(User user) throws SQLException
   {
	   System.out.println(INSERT_USER);
	   
	   try (Connection connection= getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_USER);)
	   {
		   statement.setString(1, user.getName());
		   statement.setString(2, user.getEmail());
		   statement.setString(3, user.getPhone());
		   statement.setString(4, user.getCity());
		   System.out.println(statement);
		   statement.executeUpdate();
		   
	   } 
	   catch (Exception e) 
	   {
		 e.printStackTrace();
	   }
   }
   
   
   // update user details 
   public boolean updateUser(User user) throws SQLException
   {
	   boolean rowUpdated;
	   
	   
	    try(Connection connection=getConnection();
	    	PreparedStatement statement = connection.prepareStatement(UPDATE_USER);) 
	    {
	    	statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getCity());
			statement.setInt(5, user.getId());
			
			rowUpdated = statement.executeUpdate()>0;
			
		} 
	    return rowUpdated;  
   }
   
   
   // get user details from database
   public User selectUser(int id)
   {
	  User user = null;
	  
	  try (Connection connection = getConnection();
		   PreparedStatement statement= connection.prepareStatement(SELECT_USER_BY_ID);)
	  {
		   statement.setInt(1, id);
		   System.out.println(statement);
		  
		   ResultSet rs = statement.executeQuery();
		
		while (rs.next())
		{
		   String name = rs.getString("name");
		   String email = rs.getString("email");
		   String phone = rs.getString("phone");
		   String city = rs.getString("city");
		   
		   user= new User(id, name, email, phone, city);
		}
	  } 
	  catch (Exception e) 
	  {
		e.printStackTrace();
	  }
	  return user;
   }
   
   
   
   //Select All User
  public List <User> selectAllUsers()
   {
	   List<User> users = new ArrayList<>();
	   
	   try(Connection connection = getConnection();
		   PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);) 
	   {
		  System.out.println(statement);
		  ResultSet rs = statement.executeQuery();
		  
		  while (rs.next())
		  {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String eamil = rs.getString("email");
			String phone = rs.getString("phone");
			String city = rs.getString("city");

			users.add(new User(id, name, eamil, phone, city));
		  }
 	   } 
	   catch (Exception e) 
	   {
         e.printStackTrace();
	   }
	   return users;
   }
  
  
  //deleter user
  public boolean deleteUser(int id) throws SQLException
  {
	  boolean rowDelete;
	  try(Connection connection = getConnection();
		  PreparedStatement statement =connection.prepareStatement(DELETE_USER);)
	  {
		  statement.setInt(1, id);
	      rowDelete = statement.executeUpdate()>0;
	  }
	  return rowDelete;
  }
   
}
