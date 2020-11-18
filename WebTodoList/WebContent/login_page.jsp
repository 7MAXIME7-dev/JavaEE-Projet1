<%@ page import="java.util.*,com.WebTodoList.web.jdbc.*"%>
<html>
	<head>
		<title>Web Todo List</title>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="css/style_login_page.css">
	</head>
	
	<body>
      
      	<h1>Welcome ${role1} ! </h1>

		<div class="box">
			<p>Enter your details to access the ToDo lists : </p>
		
			<form action="Register" method="post">

				<label>Username :</label> 
				<input class="placement" style="top:40%" type="text" value="${username1}" name="username"/>
				
				<div style="height:10px;"></div>
				
				<label> Password :</label> 
				<input class="placement" style="top:50%" type="password" value="${password1}" name="password"/>
				
				<input class="button_style" type="submit" value="Login"></input>

			</form>
			
			<div style="height:15px;"></div>
			
			<h2>By Maxime Bourgeois and Julien Fink</h2>			
		</div>

</body>
	
</html>
