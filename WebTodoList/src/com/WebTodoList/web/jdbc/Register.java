package com.WebTodoList.web.jdbc;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_todo_list_db")
	private DataSource dataSource;


	public void init(ServletConfig config) throws ServletException 
	{
		super.init();
		new ProfileDBUtil(dataSource);
		new ToDoDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		
		Cookie cookie = new Cookie("username1", null);
		Cookie cookie1 = new Cookie("password1", null);
		Cookie cookie2 = new Cookie("role1", null);
		
		//We get the datas entered by the user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		//We create a new profile with those datas
		Profile pl = new Profile(username, password);
		
		//We check if this profile is a student or an instructor : 
		
		//Id = 1, so this is an instructor
		if(ProfileDBUtil.check_login(pl) == 1)
		{
			//We call the welcome page for the instructor
			try 
			{
				cookie.setValue(username);
				cookie1.setValue(password);
				cookie2.setValue("Instructor");
				
				cookie.setMaxAge(60*60);
				cookie1.setMaxAge(60*60);
				cookie2.setMaxAge(60*60);
				response.addCookie(cookie);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
				
				
				session.setAttribute("username", username);
				session.setAttribute("role", "Instructor");
				listToDo_s(request, response, "instructor_page.jsp");
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
			return;
		}
		//Id = 0, we have an error
		if(ProfileDBUtil.check_login(pl) == 0)
		{
			//We call the error login page
			response.sendRedirect("error_login.jsp");
			return;
		}
		//Id != 1 and not equal to 0, it has to be a student
		else
		{
			try 
			{
				
				cookie.setValue(username);
				cookie1.setValue(password);
				cookie2.setValue("Student");
				
				cookie.setMaxAge(60*60);
				cookie1.setMaxAge(60*60);
				cookie2.setMaxAge(60*60);
				response.addCookie(cookie);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
							
				session.setAttribute("username", username);
				session.setAttribute("role", "Student");
				listToDo_s(request, response, "student_page.jsp");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}			
			return;
		}
		
	}
	
	//We also pass in a String which will be used to determine which file we go to : student or instructor
	public void listToDo_s(HttpServletRequest request, HttpServletResponse response, String file_name) throws Exception
	{
		List<ToDoList> ToDo_s = ToDoDBUtil.getToDo_s();
		request.setAttribute("TODOS_LIST", ToDo_s);
		
		//session.setAttribute("TODOS_LIST", ToDo_s);
		
		//We call the welcome page for students
		RequestDispatcher dispatcher = request.getRequestDispatcher(file_name);
		dispatcher.forward(request, response);
	}

}
