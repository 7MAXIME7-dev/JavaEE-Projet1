<%@ page import="java.util.*,com.WebTodoList.web.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>	
		<title>${sessionScope.role} Page</title>
		<link rel="stylesheet" type="text/css" href="css/style_instructor_page.css">
		
	</head>
	
	<body>
		
		<h1>Welcome ${sessionScope.username} to the ${sessionScope.role} Page !</h1>
		
		<h3 style="text-decoration: underline;">Homeworks for next session : </h3>
		
		
		<form name="form1" action="InstructorModifications" method="post">
		
			<button name="action" value="Add">Add</button> 									
		</form>


		
		<form accept-charset="UTF-8" action="InstructorModifications" method="get">
		
			<button name="save" value="Save">Save</button>
			
			<c:forEach items="${TODOS_LIST}" var="tempToDoList">
		
				<div align="center">	
											
						<textarea name= "area${tempToDoList.id}" rows="5">${tempToDoList.description}</textarea>										
				</div>
		
			</c:forEach>
		</form>		
		
		<form action="LogOut" method="post">
			
			<input id="button" type="submit" value="Log Out"></input>
				
		</form>
		
	</body>
</html>