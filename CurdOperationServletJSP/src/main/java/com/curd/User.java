package com.curd;

public class User 
{
	   private int id;
	   private String name;
	   private String email;
	   private String phone;
	   private String city;

	   
	public User(int id, String name, String email, String phone, String city) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
	}
	
	public User(String name, String email, String phone, String city) 
	{
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}


	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}


	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}


	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}


	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
}
