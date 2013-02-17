<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" >
<title>Exam results</title>
</head>
<body>


In the test : <%=(String) request.getParameter("examTitle")%> you scored:
<%=((Float) request.getAttribute("grade") ) %>
<br>
<br>
<input type="button" onClick="location.href='Student.jsp'" value="Return Home" >
<br><br>
<form action="LogoutServlet" >
<input type="submit" value="Log out" >
</form>
</body>
</html>