<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="users.*"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" >
<title>Teacher's panel</title>
</head>
<body>

<% Teacher teacher=(Teacher)session.getAttribute("user"); %>
Hello,<%=teacher.getFirstName() %>  <%=teacher.getLastName()%>
<div class="class1">
<h3>These are your current exams</h3>
</div>
<ul>
<% for(int i=0;i<teacher.examSize();i++){
%>
<FORM method="GET" ACTION="ExamResults">
<% String examtitle =teacher.getExamTitle(i); %>
<li><%=examtitle %>
<input type="hidden" name="examtitle" value=<%=examtitle%>>
<INPUT TYPE="SUBMIT" VALUE="See Results"></li><br>



</FORM>
<%} %>
</ul>
<a href="examName.jsp"><button>Create a new exam</button></a> 
<br><br> 
<form action="LogoutServlet" >
<input type="submit" value="Log out" >
</form>
</body>
</html>