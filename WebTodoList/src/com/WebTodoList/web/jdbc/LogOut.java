package com.WebTodoList.web.jdbc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException 
	{
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies.length);
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login_page.jsp");
		dispatcher.forward(request, response);
	}

}
