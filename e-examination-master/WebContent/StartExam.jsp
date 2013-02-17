<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="exam.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" >
<title>Examination</title>
</head>
<body>
<div class="class1">
This is the exam <%=(String) request.getParameter("examTitle") %>
</div>

<%Exam exam = (Exam) request.getAttribute("exam"); 
	session.setAttribute("examTaking", exam);
%>

<form action="evaluation" method="POST">
<input type="hidden" name="examName" value=<%=exam.getTestID()%> >
<% 
int k=0;
 for ( int i = 0; i < exam.questionsSize(); i++){
	
	 Question que = exam.getQuestion(i);%>
<br>
<div class="question"><b>
Question <%=i+1%>: <%=que.getQuestion() %></b>
</div>
	<%for( int j = 0 ; j < 4; j++){ %>
	<br>
		<input type="radio" name=<%=que.getQuestion()%> value=<%=k%> ><%=que.getAnswer(j) %>
		<%k++; %>
		<br>
	<%}%>
<%}%>
<br><br>
<input type="submit" value="Complete Exam">
</form>
</body>
</html>