package com.WebTodoList.web.jdbc;


import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/InstructorModifications")
public class InstructorModifications extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_todo_list_db")
	private DataSource dataSource;
	

	public void init(ServletConfig config) throws ServletException 
	{
		super.init();
		new ToDoDBUtil(dataSource);				
	}

	
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{				  	         
	     try {	    
	    	 ToDoDBUtil.newToDo();
	    	 refresh_listToDo_s(request, response, "instructor_page.jsp");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					
		List<ToDoList> ToDo_s;
		List<ToDoList> New_ToDo_s;
		try {
			ToDo_s = ToDoDBUtil.getToDo_s();
			New_ToDo_s = ToDo_s;
			
			for(int i=0; i < ToDo_s.size(); i++) {
				int e=i+1;
				ToDoList t = new ToDoList(i+1, req.getParameter("area" + e));
				New_ToDo_s.set(i, t);			
			}	

			ToDoDBUtil.SaveValues(New_ToDo_s);
			
			refresh_listToDo_s(req, resp, "instructor_page.jsp");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
	}


	private void refresh_listToDo_s(HttpServletRequest request, HttpServletResponse response, String file_name) throws Exception
	{
		HttpSession session = request.getSession();		
		List<ToDoList> ToDo_s = ToDoDBUtil.getToDo_s();	
		session.setAttribute("TODOS_LIST", ToDo_s);
		response.sendRedirect(file_name);
	}

}
