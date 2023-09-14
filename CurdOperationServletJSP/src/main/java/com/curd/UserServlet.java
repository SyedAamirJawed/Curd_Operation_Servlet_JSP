package com.curd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    private UserDAO dao;
    
    public UserServlet()
    {
        dao = new UserDAO();
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		this.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String action = req.getServletPath();
		
		try 
		{
			switch(action)
			{
			case"/new":
				showNewForm(req,resp);
				break;
				
			case"/insert":
				try 
				{
					insertUser(req,resp);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				break;
			
			case "/edit":
				try 
				{
					showEditForm(req,resp);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				break;
			case "/update":
				try 
				{
					updateUser(req,resp);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				break;
			case "/delete":
				try 
				{
					deleteUser(req,resp);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				break;
			default:
				try 
				{
					listUser(req,resp);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				break;
				
				
			}
			
		} 
		catch (Exception ex) 
		{
			throw new ServletException(ex);
		}
		
		
	}
	
	// yaha ham jsp page se user ki details ko get kar rahe insert kar rahe 
	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws  IOException, SQLException
	{
		String name = req.getParameter("name-jsp");
		String email = req.getParameter("email-jsp");
		String phone = req.getParameter("phone-jsp");
		String city = req.getParameter("city-jsp");
		
		User newUser = new User(name, email, phone, city);
		dao.insertUser(newUser);
		resp.sendRedirect("list");
	}
	
	
	// yaha se ham data base k value ko update kar rahe jo user new data aa raha hai
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name-jsp");
		String email = req.getParameter("email-jsp");
		String phone = req.getParameter("phone-jsp");
		String city = req.getParameter("city-jsp");
		
		User book = new User(id, name, email, phone, city);
		dao.updateUser(book);
		resp.sendRedirect("list");
		
	}
	
	
	
	private void listUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException
	{
		List <User> listUser = dao.selectAllUsers();
		req.setAttribute("listUser", listUser);
		RequestDispatcher despatcher = req.getRequestDispatcher("user-list.jsp");
		despatcher.forward(req, resp);
 	}
	
	
	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(req, resp);
	}
    
	
	private void showEditForm( HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException 
	{
		int id = Integer.parseInt(req.getParameter("id"));
		User existingUser = dao.selectUser(id);
		RequestDispatcher despatcher = req.getRequestDispatcher("user-form.jsp");
		req.setAttribute("user", existingUser);
		despatcher.forward(req, resp);
	}
	
	
	
	
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		dao.deleteUser(id);
		resp.sendRedirect("list");
	}
	
	
	
}
