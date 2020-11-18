package com.WebTodoList.web.jdbc;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class ProfileDBUtil 
{	
	//Because we are using 2 tables from the same database, we need to add the 'static' argument 
	//in order to tell Java which table we want to work/close 
	private static DataSource dataSource;

	public ProfileDBUtil(DataSource theDataSource) 
	{
		ProfileDBUtil.dataSource = theDataSource;
	}
	
	public static int check_login(Profile st)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		int id_number = 0;
		
		try 
		{
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select id from logins where username='" + st.username + "' && password='" + st.password + "'";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				id_number = myRs.getInt("id");
			}
			
			return id_number;			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return id_number;
		}
		finally
		{
			close(myConn, myStmt, myRs);
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
		//We have to tell which dataSource we are modifying ; in this case, ProfileDBUtil 
		ProfileDBUtil.dataSource = dataSource;
	}

}