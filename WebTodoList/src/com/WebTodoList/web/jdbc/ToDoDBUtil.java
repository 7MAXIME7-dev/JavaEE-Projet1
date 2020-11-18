package com.WebTodoList.web.jdbc;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ToDoDBUtil 
{	
	//Because we are using 2 tables from the same database, we need to add the 'static' argument 
	//in order to tell Java which table we want to work/close
	private static DataSource dataSource;

	public ToDoDBUtil(DataSource theDataSource) 
	{
		ToDoDBUtil.dataSource = theDataSource;
	}
	
	public static List<ToDoList> getToDo_s() throws Exception
	{
		List<ToDoList> ToDo_s = new ArrayList<ToDoList>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try 
		{
			myConn = dataSource.getConnection();
			System.out.println("Connection réussie getToDos");
			myStmt = myConn.createStatement();
			
			//This piece of code allows us to test if a description is empty or not
			//If it is, it means that the instructor deleted a description
			//So it has to be updated in the database...
			try 
			{
				myStmt.addBatch("SET SQL_SAFE_UPDATES = 0");
				
				myStmt.addBatch("DELETE FROM todo_list WHERE description=''");	
				
				myStmt.addBatch("set @autoid := 0");
				myStmt.addBatch("update todo_list set id= @autoid := (@autoid + 1)");
				myStmt.addBatch("ALTER TABLE todo_list AUTO_INCREMENT =1");
				
				myStmt.addBatch("SET SQL_SAFE_UPDATES = 1");
							
				myStmt.executeBatch();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			String sql= "select * from todo_list;";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String descp = myRs.getString("description");
				ToDoList tempToDo= new ToDoList(id,descp);
				ToDo_s.add(tempToDo);
			}
			
			return ToDo_s;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return ToDo_s;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	
	
	
	public static void newToDo() throws Exception
	{	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try 
		{
			myConn = dataSource.getConnection();
			System.out.println("Connection réussie new_toDo");
			myStmt = myConn.createStatement();
			
			myStmt.addBatch("SET SQL_SAFE_UPDATES = 0");
			myStmt.addBatch("INSERT INTO todo_list VALUES (NULL, 'New task here...')");
			myStmt.addBatch("SET SQL_SAFE_UPDATES = 1");
			
			myStmt.executeBatch();
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	public static void SaveValues(List<ToDoList> data) throws Exception
	{	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try 
		{
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
									
			for(int i=0; i< data.size(); i++) {
				
				
				String s1 = "UPDATE todo_list SET description = '";
				String s2 = "' WHERE id = ";
				
				myStmt.addBatch(s1 + data.get(i).getDescription() + s2 + data.get(i).getId());
				System.out.println(data.get(i).getDescription());
			}					
			myStmt.executeBatch();			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());		
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}
	
	

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) 
	{
		try
		{
			if(myStmt!=null)
			myStmt.close();
			if(myRs!=null)
			myRs.close();
			if(myConn!=null)
			myConn.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public DataSource getDataSource() 
	{
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) 
	{
		//We can't use the this. argument because we are using 2 tables from the same database
		//We have to tell which dataSource we are modifying ; in this case, ToDoDBUtil 
		ToDoDBUtil.dataSource = dataSource;
	}

}
