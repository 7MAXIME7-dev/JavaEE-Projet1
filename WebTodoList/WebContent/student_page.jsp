<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>${sessionScope.role} Page</title>
		<link rel="stylesheet" type="text/css" href="css/style_student_page.css">
	</head>
	
	<body>
		
		<h1>Welcome ${sessionScope.username} to the ${sessionScope.role} Page !</h1>
		
		<h3 style="text-decoration: underline;">Homeworks for next session : </h3>
		
		<!-- 
		
		<div id="center_box">
		
			<c:forEach var="tempToDoList" items="${TODOS_LIST}">
				
					<p>${tempToDoList.id} - ${tempToDoList.description}</p>
					
					<div style="height=10px;"></div>
					
			</c:forEach>
			
		</div>
		
		-->
		
		<c:forEach items="${TODOS_LIST}" var="tempToDoList" >
			
				
				<div align = "center">			
					<textarea disabled rows="5" cols="100", style="background-color:#000000;">${tempToDoList.description}</textarea>
				</div>
																								
		</c:forEach>

		<form action="LogOut" method="post">
						
			<input id="button" type="submit" value="Log out"></input>
					
		</form>
	
	</body>
</html>