
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="users.*"%>

	
<% 

	session.removeAttribute("admin_logged");
	User user = (User) session.getAttribute("user");
	if(user != null){
		
		if( user instanceof Student)
			response.sendRedirect("Student.jsp");
		else if( user instanceof Teacher)
			response.sendRedirect("Teacher.jsp");
	}

%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" />
<title>Welcome</title>
</head>
<body>
<h1>Welcome to the online test web application</h1>

<form action="login" method="post"  >
<p>Please enter your username and password provided to you</p>
 
 username:<input type="text" name="username"/><br>
 password:<input type="password" name="password"/><br><br>
 <input type="submit" value="submit"> 

</form>

</body>
</html>