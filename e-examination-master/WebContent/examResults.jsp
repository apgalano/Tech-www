<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page import="java.util.ArrayList" %>
  <%@ page import="java.io.*" %>
  <%@ page import="java.util.*" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" >
<title>Exam Results</title>
</head>
<body>
<div class="class1">
<h3>Here are your exam results :</h3>
</div>
<%ArrayList<String> studentNames =(ArrayList<String>)session.getAttribute("name");
ArrayList<String> studentLastNames =(ArrayList<String>)request.getSession().getAttribute("lastname"); 
%>
<%ArrayList<Float> grades =(ArrayList<Float>)session.getAttribute("grades");
%>
<ul>
<%for(int i=0;i<studentNames.size();i++){ %>
<% String name =studentNames.get(i); %>
<% float vathmos =grades.get(i); %>

<li>Name: <%=name%> <%=studentLastNames.get(i) %> Grade: <%=vathmos%><br> </li>
<%} %>
</ul>
<%
studentNames.clear();
//studentLastNames.clear();
grades.clear();
%>

<input type="button" onClick="location.href='Teacher.jsp'" value="Return Home" >
<br><br>
<form action="LogoutServlet" >
<input type="submit" value="Log out">
</form>
</body>
</html>