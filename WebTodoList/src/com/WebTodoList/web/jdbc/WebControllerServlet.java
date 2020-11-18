package com.WebTodoList.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import sun.security.util.Length;

@WebServlet("/WebControllerServlet")
public class WebControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_todo_list_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			launch_project(request, response);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void launch_project(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		try {
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				for(Cookie cook : cookies) {
					System.out.println(cook.getName());
					if(cook.getName().equals("username1")) {
						request.setAttribute("username1", cook.getValue());
						System.out.println(cook.getValue());
					}
					
					if(cook.getName().equals("password1")) {
						request.setAttribute("password1", cook.getValue());
					}
					
					if(cook.getName().equals("role1")) {
						request.setAttribute("role1", cook.getValue());
					}
				}
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login_page.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//doGet(request, response);
	}

}
