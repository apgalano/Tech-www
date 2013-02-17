<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="users.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" >
<title>Home</title>
</head>
<body>
<% 
 Student stu = (Student)session.getAttribute("user");
%>
Hello ,<%=stu.getFirstName()%> <%=stu.getLastName() %>
<br><br><br>
<div class="class1">
These tests are available to you right now:<br><br>
</div>
<ul>
<% 
	if( stu.hasAvailableExams()){
	for(int i=0; i < stu.getExamsToTakeSize(); i++){
%>
<form action="takeExam" method="GET">
<li><%=(String) stu.getExamToTake(i) %>
<input type="hidden" name="examTitle" value=<%=(String)stu.getExamToTake(i) %> >
<input type="submit" value="Take exam"></li>
<br>
</form>

<%}} %>
</ul>
<div class="class1">
These are the Exams you have taken so far:<br>
</div>
<ul>
	<%if(stu.hasTakenExams()){
  		for(int i = 0; i<stu.getExamsTakenSize();i++){
	%>
	<br>
	<li><%=stu.getExamTaken(i) %>
	
	<form action="Results" method="GET">
	<% String examName = stu.getExamTaken(i); %>
	<input type="hidden" name="examTitle" value=<%=examName%>>
	<input type="submit" value="Results"></li>


</form>


<%}} %>
</ul>
<form action="LogoutServlet" >
<input type="submit" value="Log out" >
</form>

</body>
</html>